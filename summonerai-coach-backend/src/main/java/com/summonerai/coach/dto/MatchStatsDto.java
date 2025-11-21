package com.summonerai.coach.dto;

import com.summonerai.coach.dto.riot.RiotInfoDto;
import com.summonerai.coach.dto.summoner.SummonerMatchStatsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchStatsDto {

    //game info
    private RiotInfoDto gameRiotInfoDto;
    //player stats
    private SummonerMatchStatsDto playerStats;

}
