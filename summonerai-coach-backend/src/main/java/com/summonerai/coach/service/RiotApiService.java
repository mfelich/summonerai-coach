package com.summonerai.coach.service;

import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisRequestDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RiotApiService {
    Mono<List<MatchStatsDto>> getStatsForPreviousMatches(PlayerAnalysisRequestDto request);
}
