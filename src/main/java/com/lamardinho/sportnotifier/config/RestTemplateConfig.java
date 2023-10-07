package com.lamardinho.sportnotifier.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                //.requestFactory(HttpComponentsClientHttpRequestFactory::new)
                //.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()))
                .build();
    }
}
