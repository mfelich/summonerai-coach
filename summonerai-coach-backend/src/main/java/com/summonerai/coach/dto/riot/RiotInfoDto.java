package com.summonerai.coach.dto.riot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summonerai.coach.dto.summoner.SummonerMatchStatsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiotInfoDto {

    @JsonProperty("gameDuration")
    private long gameDuration;

    @JsonProperty("gameId")
    private long gameId;

    @JsonProperty("gameMode")
    private String gameMode;

    @JsonProperty("mapId")
    private int mapId;

    @JsonProperty("participants")
    private List<SummonerMatchStatsDto> playerStats;

}
