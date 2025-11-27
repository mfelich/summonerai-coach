package com.summonerai.coach.dto.analysis;

import java.util.List;

public record PlayerAnalysisResponseDto(

        String summary,
        String playstyleProfile,

        List<String> strengths,
        List<String> weaknesses,
        List<String> consistencyIssues,

        String earlyGameAnalysis,
        String midGameAnalysis,
        String lateGameAnalysis,

        List<String> recommendations
) {}
