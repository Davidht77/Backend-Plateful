package com.dbp.projectofinal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso público a estos endpoints
                        .requestMatchers("/usuarios", "/auth/**", "/geocode/**", "/propietarios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurantes/nearby").authenticated()
                        .requestMatchers(HttpMethod.GET, "/restaurantes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/propietarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurantes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurantes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ubicaciones").authenticated()
                        .requestMatchers(HttpMethod.POST, "/ubicaciones/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/ubicaciones/**").authenticated()
                        // Restringir otros endpoints a roles específicos
                        .requestMatchers("/ubicaciones/**", "/cartas", "/platos").hasRole("PROPIETARIO")
                        .requestMatchers("/ubicaciones/**", "/cartas/**", "/platos/**", "/resenas").hasRole("CLIENTE")
                        .requestMatchers("/comentarios").hasAnyRole("PROPIETARIO", "CLIENTE")

                        // Cualquier otra solicitud necesita autenticación
                        .anyRequest().authenticated()
                )
                // Agregar el filtro JWT antes del filtro de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}


//                .cors(Customizer.withDefaults())
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/usuarios","/auth/login","/auth/register").permitAll()
//                                .requestMatchers("/ubicaciones","/cartas","/platos").hasRole("PROPIETARIO")
//                                .requestMatchers("/ubicaciones/**","/cartas/**","/platos/**","/resenas").hasRole("CLIENTE")
//                                .requestMatchers("/api/comentarios").hasAnyRole("PROPIETARIO","CLIENTE")
//                                .anyRequest().authenticated()
//                )
