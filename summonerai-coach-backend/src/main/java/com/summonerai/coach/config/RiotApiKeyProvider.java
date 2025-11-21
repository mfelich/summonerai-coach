package com.summonerai.coach.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RiotApiKeyProvider {

    @Value("${riot.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
