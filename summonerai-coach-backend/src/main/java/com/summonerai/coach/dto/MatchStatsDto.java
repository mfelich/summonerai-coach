package com.summonerai.coach.dto;

import com.summonerai.coach.dto.RiotApiResponse.Info;
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
    private Info gameInfo;
    //player stats
    private PlayerMatchStatsDto playerStats;

}
