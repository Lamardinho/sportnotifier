/*
package com.lamardinho.sportnotifier.config.security;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsManager {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(@NonNull UserRepository repository) {
        return username -> repository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(String.format("Username %s not found", username)));
    }
}
*/
