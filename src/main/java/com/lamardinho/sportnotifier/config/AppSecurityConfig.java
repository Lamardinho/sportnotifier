package com.lamardinho.sportnotifier.config;

import com.lamardinho.sportnotifier.service.AppUserDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfig {

    @NonNull
    private final AppUserDetailsService appUserDetailsService;
    @NonNull
    private final DataSource dataSource;

    @Value("${app.remember-me.configurer-key}")
    private String rememberMeConfigurerKey;

    @Value("${app.remember-me.keep-in-db}")
    private boolean rememberMeKeepInDb;

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        val loginPostfixUrl = "/login";
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .userDetailsService(appUserDetailsService)
                .httpBasic(configurer -> {
                })
                .rememberMe(c -> c
                        .key(rememberMeConfigurerKey)
                        .alwaysRemember(true)
                        .rememberMeCookieName("sportnotifier-remember-me")
                        .tokenValiditySeconds(86400)
                        .tokenRepository(persistentTokenRepository())
                )
                .formLogin(c -> c
                        .loginPage(loginPostfixUrl)
                        .defaultSuccessUrl("/", false)
                        .permitAll()
                )
                .logout(c -> c
                        .deleteCookies(rememberMeConfigurerKey)
                        .logoutSuccessUrl(loginPostfixUrl)
                        .permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/actuator/env",
                                "/h2-console/**"
                        ).hasAuthority("APP_ADMIN")

                        .requestMatchers(
                                "/public/**",
                                "/api/public/**",
                                loginPostfixUrl,
                                "/registration",

                                // actuator:
                                "/actuator",
                                "/actuator/health/**",
                                "/actuator/metrics/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

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

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        if (rememberMeKeepInDb) {
            val tokenRepository = new JdbcTokenRepositoryImpl();
            tokenRepository.setDataSource(dataSource);
            return tokenRepository;
        } else {
            return new InMemoryTokenRepositoryImpl();
        }
    }
}
