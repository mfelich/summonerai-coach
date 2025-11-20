package com.summonerai.coach.service;

import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.SummonerInfoDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RiotApiService {
    Mono<SummonerInfoDto> getSummonerByName(String summonerName);
    Mono<List<String>> getMatchHistoryIds(String summonerName);
    Mono<List<MatchStatsDto>> getStatsForPreviousMatches(String summonerName);
}
