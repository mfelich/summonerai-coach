package com.summonerai.coach.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisRequestDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisResponseDto;
import com.summonerai.coach.service.OpenAiService;
import com.summonerai.coach.service.RiotApiService;
import org.springframework.ai.chat.client.ChatClient;
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

    private List<MatchStatsDto> getMatchStatsObjects(PlayerAnalysisRequestDto request) {
        Mono<List<MatchStatsDto>> mono = riotApiService.getStatsForPreviousMatches(request);
        List<MatchStatsDto> matchStatsList = mono.block();

        return matchStatsList;
    }

    @Override
    public PlayerAnalysisResponseDto analyzePlayerBySummonerName(PlayerAnalysisRequestDto request) throws JsonProcessingException {

        List<MatchStatsDto> matchStatsDto = getMatchStatsObjects(request);
        String rank = request.getRank();

        ObjectMapper objectMapper = new ObjectMapper();
        String statsJson = objectMapper.writeValueAsString(matchStatsDto);

        String prompt = """
                String prompt = ""\"
                Analyze the following League of Legends match data for a single player.
                
                MATCH_DATA:
                %s
                
                PLAYER_INFO:
                - Rank: %s
                
                Use the rank not as the actual displayed skill, but as the benchmark reference for evaluating performance.\s
                You must compare the player's statistics EXCLUSIVELY to the real-world performance expectations for that rank.
                
                RANK PERFORMANCE BENCHMARKS (use these strictly):
                
                IRON:
                - KDA: 1.0–1.8
                - CS/min: 2.0–4.0
                - Damage share: 10–16%%
                - Vision score: 5–12
                - Objective participation: very low
                - Mistakes: extremely high frequency, poor map awareness
                
                BRONZE:
                - KDA: 1.5–2.2
                - CS/min: 3.0–4.5
                - Damage share: 14–18%%
                - Vision score: 8–15
                - Objective participation: low
                - Mistakes: frequent deaths, inconsistency, weak macro
                
                SILVER:
                - KDA: 1.8–2.5
                - CS/min: 4.0–5.2
                - Damage share: 15–20%%
                - Vision score: 10–18
                - Objective participation: inconsistent
                - Mistakes: poor macro, weak map awareness, bad death timing
                
                GOLD:
                - KDA: 2.0–3.0
                - CS/min: 5.0–5.8
                - Damage share: 18–22%%
                - Vision score: 15–22
                - Objective participation: moderate
                - Mistakes: moderate—usually laning errors or macro misreads
                
                PLATINUM:
                - KDA: 2.2–3.5
                - CS/min: 5.5–6.5
                - Damage share: 20–25%%
                - Vision score: 18–26
                - Objective participation: good
                - Mistakes: better decision-making, fewer forced fights
                
                EMERALD:
                - KDA: 2.5–4.0
                - CS/min: 5.7–6.7
                - Damage share: 22–28%%
                - Vision score: 20–28
                - Objective participation: very good
                
                DIAMOND:
                - KDA: 3.0–5.0
                - CS/min: 6.0–7.2
                - Damage share: 23–30%%
                - Vision score: 22–30
                - Objective participation: high
                - Mistakes: rare, mostly micro or tempo issues
                
                MASTER+:
                - KDA: 3.5–6.0+
                - CS/min: 6.5–8.0
                - Damage share: 25–32%%
                - Vision score: 25–35
                - Objective participation: very high
                - Mistakes: low, mostly matchup-specific or map tempo errors
                
                CHALLENGER:
                - KDA: 4.0–8.0+
                - CS/min: 7.0–9.5
                - Damage share: 28–36%%
                - Vision score: 30–40+
                - Objective participation: extremely high
                - Mistakes: minimal; expect nearly perfect decision-making and optimal resource usage
                
                
                YOUR ANALYSIS MUST USE THESE BENCHMARKS STRICTLY:
                - If a player statistically performs far ABOVE their stated rank, praise and reward highly.
                - If a player performs BELOW expectations, critique accordingly.
                - Never mix ranks. Use ONLY the benchmarks for the provided rank.
                
                
                NOW PERFORM THE FOLLOWING:
                
                1. Compare player's performance exclusively to their RANK benchmark.
                2. Adjust expectations for:
                   - gold efficiency
                   - KDA norms
                   - damage contribution
                   - laning fundamentals
                   - objective participation
                   - vision
                   - map awareness
                   - risk patterns
                   - macro rotations
                   - teamfighting consistency
                
                3. Extract playstyle patterns, consistency issues, repeated mistakes, strengths.
                
                4. Analyze early/mid/late game decision-making.
                
                5. Provide champion-specific insights and best practices.
                
                6. Provide trends across matches: KDA, gold, damage, vision, objectives.
                
                7. Provide 0–10 ratings (where 10 means Challenger-level mastery for that category).
                
                FORMAT REQUIREMENTS:
                Return ONLY valid JSON structured in this exact schema:
                
                {
                  "summary": "string",
                  "playstyleProfile": "string",
                
                  "strengths": ["string"],
                  "weaknesses": ["string"],
                  "consistencyIssues": ["string"],
                
                  "earlyGameAnalysis": "string",
                  "midGameAnalysis": "string",
                  "lateGameAnalysis": "string",
                
                  "laneSpecificInsights": {
                    "lane": "string",
                    "analysis": "string",
                    "laneStrengths": ["string"],
                    "laneWeaknesses": ["string"]
                  },
                
                  "championInsights": [
                    {
                      "champion": "string",
                      "analysis": "string",
                      "bestPractices": ["string"]
                    }
                  ],
                
                  "statisticalTrends": {
                    "kdaTrend": "string",
                    "damageOutputTrend": "string",
                    "goldEfficiency": "string",
                    "visionControlTrend": "string",
                    "objectiveControlTrend": "string"
                  },
                
                  "ratings": {
                    "mechanics": "double",
                    "macro": "double",
                    "positioning": "double",
                    "teamfightImpact": "double",
                    "mapAwareness": "double",
                    "consistency": "double"
                  },
                
                  "recommendations": ["string"]
                }
                
                Return ONLY the JSON. No additional text.
                
                """.formatted(statsJson,rank.toUpperCase());


        return chatClient
                .prompt(prompt)
                .call()
                .entity(PlayerAnalysisResponseDto.class);
    }
}
