package com.summonerai.coach.dto.analysis;

import com.summonerai.coach.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerAnalysisRequestDto {
    private String summonerName;
    private Region region;
    private String rank;
}
