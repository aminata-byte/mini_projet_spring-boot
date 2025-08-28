package com.exercice.mini_projet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Mini-Projet API",
                version = "v1",
                description = "CRUD des livres (Book)"
        )
)
public class OpenApiConfig {}
