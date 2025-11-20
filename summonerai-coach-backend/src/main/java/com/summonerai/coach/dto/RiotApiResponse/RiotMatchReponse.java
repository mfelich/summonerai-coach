package com.summonerai.coach.dto.RiotApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiotMatchReponse {
    private MetaData metadata;
    private Info info;
}
