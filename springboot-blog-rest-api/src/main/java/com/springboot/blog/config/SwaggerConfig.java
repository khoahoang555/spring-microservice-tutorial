package com.springboot.blog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Spring Boot Blog REST APIs")
                        .description("Spring Boot Blog REST API Documentation")
                        .version("1")
                        .termsOfService("Terms of service")
                        .license(new License().name("License of API")
                                .url("https://springdoc.org/"))
                        .contact(new Contact().name("Khoa Pham")
                                .email("khoahoanghh@gmail.com")
                                .url("https://swagger.io/")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
