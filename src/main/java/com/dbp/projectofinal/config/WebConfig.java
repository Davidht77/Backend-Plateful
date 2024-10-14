package com.dbp.projectofinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permitir todas las rutas
                .allowedOrigins("*")  // Permitir todos los orígenes (ajusta según tus necesidades)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}