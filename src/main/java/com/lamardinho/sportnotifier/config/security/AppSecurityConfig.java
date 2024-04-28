package com.lamardinho.sportnotifier.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfig {

    @NonNull
    private final AppUserDetailsService appUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        return http
                .httpBasic(configurer -> {
                })
                .authorizeHttpRequests(authorize -> authorize
                        //.anyRequest().authenticated()

                        .requestMatchers(
                                "/actuator/env",
                                "/h2-console/**"
                        ).hasRole("APP_ADMIN")

                        .requestMatchers(
                                "/logout",
                                "/check-authenticated.html"
                        ).authenticated()

                        .requestMatchers(
                                "/api/register/**",
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

                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(appUserDetailsService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        val authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
