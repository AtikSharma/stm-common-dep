package com.taskmanager.common.client;

import com.taskmanager.common.RequestContext;
import com.taskmanager.common.constants.CommonConstants;
import com.taskmanager.common.model.User;
import com.taskmanager.common.util.JwtUtils;
import com.taskmanager.common.util.URLBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class UserServiceClientImpl implements UserServiceClient {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value(value = "${taskmanager.userservice.instance.name:" + CommonConstants.USER_SVC + "}")
    private String serviceName;

    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceClientImpl(RestTemplate restTemplate, JwtUtils jwtUtils) {
        this.restTemplate = restTemplate;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User getUserDetailsByUsername(String username) {
        String url = URLBuilder.builder().protocol("http").serviceName(serviceName)
                .addPathSegment(CommonConstants.BASE_URL_USER_V1)
                .addPathSegment(CommonConstants.PATH_USERNAME)
                .addPathSegment(username).build();
        RequestContext.setAuthorizationToken(jwtUtils.generateSystemToken());
        return restTemplate.exchange(URI.create(url), HttpMethod.GET, HttpEntity.EMPTY, User.class).getBody();
    }

    @Override
    public User getUserDetailsById(String userId) {
        String url = URLBuilder.builder().protocol("http").serviceName(serviceName)
                .addPathSegment(CommonConstants.BASE_URL_USER_V1)
                .addPathSegment(userId).build();
        RequestContext.setAuthorizationToken(jwtUtils.generateSystemToken());
        return restTemplate.exchange(URI.create(url), HttpMethod.GET, HttpEntity.EMPTY, User.class).getBody();
    }

    @Override
    public List<User> getAllUsers() {
        String url = URLBuilder.builder().protocol("http").serviceName(serviceName)
                .addPathSegment(CommonConstants.BASE_URL_USER_V1)
                .build();
        RequestContext.setAuthorizationToken(jwtUtils.generateSystemToken());
        return (List<User>) restTemplate.exchange(URI.create(url), HttpMethod.GET, HttpEntity.EMPTY, List.class).getBody();
    }

}
