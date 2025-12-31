package com.taskmanager.common.configuration;

import com.taskmanager.common.RequestContext;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Value(value = "${spring.application.name:Smart Task Management System}")
    private String title;

    @Value(value = "${swagger.version:3.0}")
    private String swaggerVersion;

    private static final String EXAMPLE_CORRELATION_HEADER = "d64cf01b-ce65-4a57-ac3e-f7fa09e1a87f";

    @Bean
    OpenAPI customOpenAPI() {

        StringBuilder sb = new StringBuilder();
        sb.append("APIs for ");
        sb.append(title);

        Parameter correlationHeaderVersion = new Parameter().in(ParameterIn.HEADER.toString())
                .name(RequestContext.HEADER_FIELD_CORRELATION_ID).schema(new StringSchema())
                .example(EXAMPLE_CORRELATION_HEADER).required(true);

        return new OpenAPI().info(new Info().title(title).version(swaggerVersion).description(sb.toString()))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().name("Authorization")
                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")).addParameters(RequestContext.HEADER_FIELD_CORRELATION_ID, correlationHeaderVersion));
    }

}
