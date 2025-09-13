package com.taskmanager.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiPredicate;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.taskmanager.common.RequestContext;
import com.taskmanager.common.constants.CommonConstants;
import com.taskmanager.common.constants.ErrorConstants;
import com.taskmanager.common.constants.JwtConstants;
import com.taskmanager.common.enums.Role;
import com.taskmanager.common.exception.CustomSecurityException;
import com.taskmanager.common.model.JwtToken;
import com.taskmanager.common.model.UserBase;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${jwt.secretkey}")
    private String SECRET_KEY;

    @Value("${jwt.access.expiration:120}")
    private long accessTokenExpiration;

    @Value("${jwt.access.expiration:300}")
    private long systemAccessTokenExpiration;

    @Value("${jwt.refresh.expiration:300}")
    private long refreshTokenExpiration;

    public static class TokenType {
        public static final String JWT = "JWT";
        public static final String REFRESH = "REFRESH";
    }

    public JwtToken generateJwt(UserBase userBase) {
        Instant now = Instant.now();
        Date currentDate = Date.from(now);
        Date expiryDate = Date.from(now.plus(accessTokenExpiration, ChronoUnit.SECONDS));
        final String randomUUID = UUID.randomUUID().toString();

        String accessToken = Jwts.builder().header().add(JwtConstants.TYPE, TokenType.JWT).and()
                .issuer(JwtConstants.JWT_ISSUER).subject(JwtConstants.SUBJECT).id(randomUUID).expiration(expiryDate)
                .issuedAt(currentDate).claim(JwtConstants.ID, userBase.getId())
                .claim(JwtConstants.CORRELATION_ID, RequestContext.getCorrelationId())
                .claim(JwtConstants.USERNAME, userBase.getUsername()).claim(JwtConstants.EMAIL, userBase.getEmail())
                .claim(JwtConstants.ROLE, userBase.getRole()).signWith(getKey()).compact();

        String refreshToken = generateRefreshToken(now, userBase);

        return JwtToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    private String generateRefreshToken(Instant now, UserBase userBase) {
        Date expiryDate = Date.from(now.plus(refreshTokenExpiration, ChronoUnit.SECONDS));
        final String randomUUID = UUID.randomUUID().toString();
        return Jwts.builder().header().add(JwtConstants.TYPE, TokenType.REFRESH).and()
                .issuer(JwtConstants.JWT_ISSUER).subject(JwtConstants.SUBJECT).id(randomUUID).expiration(expiryDate)
                .issuedAt(Date.from(now)).claim(JwtConstants.USER_ID, userBase.getId()).signWith(getKey()).compact();
    }

    public String generateSystemToken() {
        Instant now = Instant.now();
        Date currentDate = Date.from(now);
        Date expiryDate = Date.from(now.plus(systemAccessTokenExpiration, ChronoUnit.SECONDS));
        final String randomUUID = UUID.randomUUID().toString();
        return Jwts.builder().header().add(JwtConstants.TYPE, TokenType.JWT).and()
                .issuer(JwtConstants.JWT_ISSUER_SYSTEM).subject(JwtConstants.SUBJECT).id(randomUUID)
                .expiration(expiryDate).issuedAt(currentDate)
                .claim(JwtConstants.CORRELATION_ID, RequestContext.getCorrelationId())
                .claim(JwtConstants.USERNAME, JwtConstants.JWT_ISSUER_SYSTEM).claim(JwtConstants.ROLE, Role.SYSTEM)
                .signWith(getKey()).compact();
    }

    private Key getKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload();
    }


    private Claims validateAuthorizationHeader(String authorizationHeader) {
        Claims claims = null;
        try {
            String token = extractBearerToken(authorizationHeader);
            claims = validateTokenExpiration(token);
        } catch (Exception e) {
            throw new CustomSecurityException(HttpStatus.UNAUTHORIZED);
        }
        return claims;
    }

    private String extractBearerToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(CommonConstants.BEARER)) {
            throw new CustomSecurityException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }
        return authorizationHeader.substring(7);
    }

    private Claims validateTokenExpiration(String token) {
        Claims claims = extractClaims(token);
        if (Objects.nonNull(claims.getExpiration()) && claims.getExpiration().before(new Date())) {
            throw new CustomSecurityException(HttpStatus.UNAUTHORIZED,
                    (claims.getIssuer().equals(JwtConstants.JWT_ISSUER_SYSTEM))
                            ? ErrorConstants.SYSTEM_ACCESS_TOKEN_EXPIRED
                            : String.format(ErrorConstants.ACCESS_TOKEN_EXPIRED, claims.get(JwtConstants.USERNAME)));
        }
        return claims;
    }

    public void validateAccess(String authorizationHeader, List<Role> requiredRoles) throws CustomSecurityException {
        Role userRole = extractRole(authorizationHeader);
        if (!requiredRoles.contains(userRole)) {
            throw new CustomSecurityException(HttpStatus.FORBIDDEN, ErrorConstants.ACCESS_DENIED);
        }
    }

    public void validateAccess(String authorizationHeader, Role requiredRole) throws CustomSecurityException {
        Role userRole = extractRole(authorizationHeader);
        if (!requiredRole.equals(userRole)) {
            throw new CustomSecurityException(HttpStatus.FORBIDDEN, ErrorConstants.ACCESS_DENIED);
        }
    }

    private Role extractRole(String authorizationHeader) {
        Claims claims = validateAuthorizationHeader(authorizationHeader);
        if (!claims.containsKey(JwtConstants.ROLE)) {
            throw new CustomSecurityException(HttpStatus.UNAUTHORIZED, ErrorConstants.ROLE_UNDEFINED);
        }
        return Role.valueOf((String) claims.get(JwtConstants.ROLE));
    }

    public boolean isTokenExpired(Claims claims) {
        return Objects.nonNull(claims.getExpiration()) && claims.getExpiration().before(new Date());
    }

    public boolean isTokenTypeValid(String token, String refresh) {
        Header header = Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getHeader();
        BiPredicate<Header, String> isTokenTypeValid = (c, t) -> c.getType().equals(t);
        return isTokenTypeValid.test(header, refresh);
    }
}
