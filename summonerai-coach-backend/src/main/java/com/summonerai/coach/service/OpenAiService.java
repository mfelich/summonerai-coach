package com.summonerai.coach.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.summonerai.coach.dto.OpenAiReposne;

public interface OpenAiService {
    OpenAiReposne analyzePlayerBySummonerName(String prompt) throws JsonProcessingException;
}
