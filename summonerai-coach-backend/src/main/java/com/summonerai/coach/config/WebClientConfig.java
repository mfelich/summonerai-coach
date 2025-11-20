package com.summonerai.coach.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final RiotApiKeyProvider riotApiKeyProvider;

    public WebClientConfig(RiotApiKeyProvider riotApiKeyProvider) {
        this.riotApiKeyProvider=riotApiKeyProvider;
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Riot-Token", riotApiKeyProvider.getApiKey())
                .build();
    }

}
