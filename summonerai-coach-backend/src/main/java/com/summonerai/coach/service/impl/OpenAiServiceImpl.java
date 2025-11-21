package com.summonerai.coach.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.OpenAiReposne;
import com.summonerai.coach.service.OpenAiService;
import com.summonerai.coach.service.RiotApiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private ChatClient chatClient;
    private RiotApiService riotApiService;

    public OpenAiServiceImpl(ChatClient chatClient, RiotApiService riotApiService) {
        this.chatClient=chatClient;
        this.riotApiService=riotApiService;
    }

    private List<MatchStatsDto> getMatchStatsObjects(String summonerName) {
        Mono<List<MatchStatsDto>> mono = riotApiService.getStatsForPreviousMatches(summonerName);
        List<MatchStatsDto> matchStatsList = mono.block();

        return matchStatsList;
    }

    @Override
    public OpenAiReposne analyzePlayerBySummonerName(String summonerName) throws JsonProcessingException {

        List<MatchStatsDto> matchStatsDto = getMatchStatsObjects(summonerName);

        ObjectMapper objectMapper = new ObjectMapper();
        String statsJson = objectMapper.writeValueAsString(matchStatsDto);

        String prompt = """
                            Analyze the following list of match statistics for a League of Legends player and provide insights:
                            %s
                            - Summarize the player's strengths and weaknesses.
                            - Suggest areas for improvement.
                            - Give tips based on champion performance.
                            Return the response as JSON with fields: { "summary": "...", "strengths": ["..."], "weaknesses": ["..."], "recommendations": ["..."] }
                """.formatted(statsJson);


        return chatClient
                .prompt(prompt)
                .call()
                .entity(OpenAiReposne.class);
    }
}
