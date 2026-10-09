package com.ofss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ofss.security.JwtAuthFilter;
import com.ofss.security.RestAccessDeniedHandler;
import com.ofss.security.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestAccessDeniedHandler restAccessDeniedHandler;

    public SecurityConfig(
            JwtAuthFilter jwtAuthFilter,
            RestAuthenticationEntryPoint restAuthenticationEntryPoint,
            RestAccessDeniedHandler restAccessDeniedHandler
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                        .accessDeniedHandler(restAccessDeniedHandler)
                )

                .authorizeHttpRequests(authorize -> authorize

                        // ADMIN and MANAGER: view Merchant data
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/merchants"
                        )
                        .hasAnyRole("ADMIN", "MANAGER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/merchants/**"
                        )
                        .hasAnyRole("ADMIN", "MANAGER")

                        // ADMIN: create Merchant
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/merchants"
                        )
                        .hasRole("ADMIN")

                        // ADMIN: full update
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/merchants/**"
                        )
                        .hasRole("ADMIN")

                        // ADMIN: partial update
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/merchants/**"
                        )
                        .hasRole("ADMIN")

                        // ADMIN: delete
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/merchants/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers("/error")
                        .permitAll()

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}