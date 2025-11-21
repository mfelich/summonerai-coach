package com.summonerai.coach.dto.riot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiotMetaDataDto {

    @JsonProperty("matchId")
    private String matchId;

}
