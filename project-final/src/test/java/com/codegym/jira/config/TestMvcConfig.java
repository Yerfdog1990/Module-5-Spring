package com.codegym.jira.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@TestConfiguration
public class TestMvcConfig {

    @Bean
    @Primary
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builder = Mockito.mock(RestTemplateBuilder.class);
        // Mock the method chain for RestTemplateBuilder used in MvcConfig.restTemplate
        Mockito.when(builder.setConnectTimeout(Mockito.any(Duration.class))).thenReturn(builder);
        Mockito.when(builder.setReadTimeout(Mockito.any(Duration.class))).thenReturn(builder);
        // When build is called, return a mock RestTemplate to avoid real HTTP configuration
        Mockito.when(builder.build()).thenReturn(Mockito.mock(RestTemplate.class));
        return builder;
    }
}
