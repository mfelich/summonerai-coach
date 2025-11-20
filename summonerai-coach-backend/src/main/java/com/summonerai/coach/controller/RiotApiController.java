package com.summonerai.coach.controller;

import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.service.RiotApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/riot/api/controller")
public class RiotApiController {

    private RiotApiService service;

    public RiotApiController(RiotApiService service) {
        this.service=service;
    }

    @GetMapping("/summoner/{summonerName}")
    ResponseEntity<Mono<List<MatchStatsDto>>> getMatchHistory(@PathVariable("summonerName") String summonerName) {
        return ResponseEntity.ok(service.getStatsForPreviousMatches(summonerName));
    }
}
