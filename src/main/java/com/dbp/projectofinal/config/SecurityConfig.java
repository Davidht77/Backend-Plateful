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
                        .requestMatchers("/usuarios", "/auth/**", "/geocode/**", "/propietarios/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/platos/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.GET,"/restaurantes/**").hasAnyRole("PROPIETARIO","CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/cartas/**").hasAnyRole("PROPIETARIO","CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/platos/carta/**").hasAnyRole("PROPIETARIO","CLIENTE")
                        .requestMatchers(HttpMethod.POST,"/resenas/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/resenas/**").hasAnyRole("PROPIETARIO","CLIENTE")
                        .requestMatchers(HttpMethod.POST,"/comentarios/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/comentarios/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST,"/restaurantes/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PATCH,"/restaurantes/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PATCH,"/restaurantes/edit/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PUT,"/restaurantes/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.POST,"/cartas/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PATCH,"/cartas/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PUT,"/cartas/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.POST,"/platos/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PATCH,"/platos/**").hasRole("PROPIETARIO")
                        .requestMatchers(HttpMethod.PUT,"/platos/**").hasRole("PROPIETARIO")
                        // Restringir otros endpoints a roles específicos
                        .requestMatchers("/platos/**").hasRole("PROPIETARIO")
                        .requestMatchers("/platos/disponibles","/platos/carta/**","/comentarios/usuario/**").hasRole("CLIENTE")
                        .requestMatchers("/ubicaciones/**","/comentarios/resena/**","/resenas/restaurante/**","/resenas/usuario/**", "/usuarios/me", "/restaurantes/nearby", "/restaurantes/**").hasAnyRole("PROPIETARIO", "CLIENTE")

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
