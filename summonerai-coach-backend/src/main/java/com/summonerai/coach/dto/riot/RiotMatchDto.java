package com.summonerai.coach.dto.riot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatchDto {

    @JsonProperty("metadata")
    private RiotMetaDataDto metadata;

    @JsonProperty("info")
    private RiotInfoDto riotInfoDto;
}
