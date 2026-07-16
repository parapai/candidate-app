package com.vai.test.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Component
public class SwaggerLogConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    public SwaggerLogConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String port = environment.getProperty("local.server.port");
        if (port == null) {
            port = environment.getProperty("server.port", "8080");
        }
        
        String swaggerPath = environment.getProperty("springdoc.swagger-ui.path", "/swagger-ui/index.html");
        
        if (!swaggerPath.startsWith("/")) {
            swaggerPath = "/" + swaggerPath;
        }

        System.out.println("\n====================================================");
        System.out.println("Aplikasi Spring Boot kamu berhasil dijalankan!");
        System.out.println("Access Swagger UI di: http://localhost:" + port + swaggerPath);
        System.out.println("Access OpenAPI Docs di: http://localhost:" + port + "/v3/api-docs");
        System.out.println("====================================================\n");
    }
    
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                // 1. Daftarkan skema keamanan Bearer Token
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                // 2. Terapkan proteksi gembok ini ke seluruh API secara global
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
