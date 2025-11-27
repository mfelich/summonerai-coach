package com.summonerai.coach.dto.analysis;

import com.summonerai.coach.enums.Region;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerAnalysisRequestDto {

    @NotBlank(message = "Summoner name is required.")
    private String summonerName;

    @NotNull(message = "Region is required.")
    private Region region;

    @NotBlank(message = "Summoner rank is required.")
    private String rank;

}
