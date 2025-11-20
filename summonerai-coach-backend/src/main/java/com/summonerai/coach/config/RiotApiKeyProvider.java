package com.summonerai.coach.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class RiotApiKeyProvider {

    private final String apiKey;

    public RiotApiKeyProvider() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("RIOT_API_KEY");
    }

    public String getApiKey() {
        return apiKey;
    }
}
