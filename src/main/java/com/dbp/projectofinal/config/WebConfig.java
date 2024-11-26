package com.dbp.projectofinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir específicamente tu dominio frontend
        corsConfiguration.setAllowedOrigins(Arrays.asList(
            "https://main.d2wpgy1h5q22ko.amplifyapp.com" // Agrega tu dominio frontend
        ));

        // Opcional: Permitir todos los demás orígenes
        corsConfiguration.addAllowedOriginPattern("*");

        // Métodos HTTP permitidos
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Permitir credenciales (si es necesario)
        corsConfiguration.setAllowCredentials(true);

        // Permitir todos los encabezados
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
