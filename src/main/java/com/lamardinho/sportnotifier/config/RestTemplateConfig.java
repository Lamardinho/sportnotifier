package com.lamardinho.sportnotifier.config;

import lombok.NonNull;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        val template = new RestTemplate(clientHttpRequestFactory());

        template.getMessageConverters()
                .stream()
                .filter(StringHttpMessageConverter.class::isInstance)
                .map(StringHttpMessageConverter.class::cast)
                .forEach(converter -> converter.setDefaultCharset(StandardCharsets.UTF_8));

        return template;
    }

    private @NonNull ClientHttpRequestFactory clientHttpRequestFactory() {
        val factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(10_000);
        factory.setReadTimeout(10_000);

        return new BufferingClientHttpRequestFactory(factory);
    }
}
