package com.summonerai.coach.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.summonerai.coach.dto.analysis.PlayerAnalysisResponseDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisRequestDto;
import com.summonerai.coach.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai-analyze")
@RequiredArgsConstructor
public class OpenAiController {

    private final OpenAiService service;

    @PostMapping
    public ResponseEntity<PlayerAnalysisResponseDto> analyzePlayer(@RequestBody PlayerAnalysisRequestDto request) throws JsonProcessingException {
        return ResponseEntity.ok(service.analyzePlayerBySummonerName(request));
    }
}
