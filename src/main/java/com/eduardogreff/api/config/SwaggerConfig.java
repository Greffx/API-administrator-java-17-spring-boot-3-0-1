package com.eduardogreff.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personal Project with Java 17  and Spring Boot 3.0.1")
                        .version("v1")
                        .description("This API is for studies purpose, it has a full CRUD operation, got pagination attribute, linked with MySQL DB, organized with layers, such as controllers, repositories, services, entities, mapper, exceptions and configurations.")
                        .termsOfService("https://studies.eduardogreff.com.br")
                        .license(
                                new License()
                                        .name("Apacha 2.0")
                                        .url("https://studies.eduardogreff.com.br")));

    }
}
