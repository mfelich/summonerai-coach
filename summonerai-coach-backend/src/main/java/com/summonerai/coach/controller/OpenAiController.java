package com.summonerai.coach.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.summonerai.coach.dto.OpenAiReposne;
import com.summonerai.coach.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai-generate")
public class OpenAiController {

    private OpenAiService service;

    public OpenAiController(OpenAiService service) {
        this.service=service;
    }

    @PostMapping("/{summonerName}")
    public ResponseEntity<OpenAiReposne> analyzePlayer(@PathVariable("summonerName") String summonerName) throws JsonProcessingException {
        return ResponseEntity.ok(service.analyzePlayerBySummonerName(summonerName));
    }
}
