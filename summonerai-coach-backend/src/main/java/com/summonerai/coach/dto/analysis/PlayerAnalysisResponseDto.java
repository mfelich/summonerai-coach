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

        LaneInsights laneSpecificInsights,

        List<ChampionInsight> championInsights,

        StatisticalTrends statisticalTrends,

        Ratings ratings,

        List<String> recommendations
) {

    public record LaneInsights(
            String lane,
            String analysis,
            List<String> laneStrengths,
            List<String> laneWeaknesses
    ) {}

    public record ChampionInsight(
            String champion,
            String analysis,
            List<String> bestPractices
    ) {}

    public record StatisticalTrends(
            String kdaTrend,
            String damageOutputTrend,
            String goldEfficiency,
            String visionControlTrend,
            String objectiveControlTrend
    ) {}

    public record Ratings(
            double mechanics,
            double macro,
            double positioning,
            double teamfightImpact,
            double mapAwareness,
            double consistency
    ) {}
}
