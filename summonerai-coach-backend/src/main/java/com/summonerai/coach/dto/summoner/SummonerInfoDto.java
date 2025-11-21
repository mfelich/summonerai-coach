package com.summonerai.coach.dto.summoner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummonerInfoDto {
    private String puuid;
    private String gameName;
    private String tagLine;
}
