
package com.lamardinho.sportnotifier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class AppSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers(
                        "/h2-console/**",
                        "/actuator",
                        "/actuator/health"
                );
    }

    @Bean
    @Profile("no_security")
    public WebSecurityCustomizer webSecurityCustomizerForLocal() {
        return web -> web
                .ignoring().anyRequest();
    }
}
