package com.summonerai.coach.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.summonerai.coach.dto.analysis.PlayerAnalysisResponseDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisRequestDto;

public interface OpenAiService {
    PlayerAnalysisResponseDto analyzePlayerBySummonerName(PlayerAnalysisRequestDto prompt) throws JsonProcessingException;
}
