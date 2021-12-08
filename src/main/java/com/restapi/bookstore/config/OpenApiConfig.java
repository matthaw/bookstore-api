package com.restapi.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenapi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 17 and Spring boot 2.6.0")
                        .version("v1")
                        .description("")
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("MIT").url("https://springdoc.org")));
    }
}