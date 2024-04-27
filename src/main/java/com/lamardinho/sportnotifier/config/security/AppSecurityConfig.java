package com.lamardinho.sportnotifier.config.security;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        return http
                .httpBasic(configurer -> {
                })
                .authorizeHttpRequests(authorize -> authorize
                        //.anyRequest().authenticated()

                        .requestMatchers(
                                "/actuator/env"
                        ).hasRole("APP_ADMIN")

                        .requestMatchers(
                                "/logout",
                                "/check-authenticated.html"
                        ).authenticated()

                        .requestMatchers(
                                "/register/**",
                                "/login"
                        ).anonymous()

                        .requestMatchers(
                                "/public/**",
                                "/registration.xml",
                                "/swagger-ui/**",

                                // actuator:
                                "/actuator",
                                "/actuator/health/**",
                                "/actuator/metrics/**"
                        ).permitAll()
                )
                /*.exceptionHandling(c -> c.authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/login123")))*/
                .build();
    }
}
