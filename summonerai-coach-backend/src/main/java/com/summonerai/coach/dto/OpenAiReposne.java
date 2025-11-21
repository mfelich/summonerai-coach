package com.summonerai.coach.dto;

import java.util.List;

public record OpenAiReposne(
        String summary,
        List<String> strengths,
        List<String> weaknesses,
        List<String> recommendations
) {
}
