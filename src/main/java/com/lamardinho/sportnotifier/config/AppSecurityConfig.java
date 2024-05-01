package com.lamardinho.sportnotifier.config;

import com.lamardinho.sportnotifier.service.AppUserDetailsService;
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
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .httpBasic(configurer -> {
                })
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/deny").denyAll() // for my tests

                        .requestMatchers(
                                "/actuator/env",
                                "/h2-console/**"
                        ).hasAuthority("APP_ADMIN")

                        .requestMatchers(
                                "/public/**",
                                "/api/public/**",
                                "/login",

                                // actuator:
                                "/actuator",
                                "/actuator/health/**",
                                "/actuator/metrics/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin(configurer -> configurer
                        .defaultSuccessUrl("/main.html", false))
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
