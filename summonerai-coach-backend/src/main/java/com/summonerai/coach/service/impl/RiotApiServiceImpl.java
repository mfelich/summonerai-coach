package com.summonerai.coach.service.impl;

import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.SummonerInfoDto;
import com.summonerai.coach.dto.RiotApiResponse.Info;
import com.summonerai.coach.dto.PlayerMatchStatsDto;
import com.summonerai.coach.dto.RiotApiResponse.RiotMatchReponse;
import com.summonerai.coach.service.RiotApiService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class RiotApiServiceImpl implements RiotApiService {

    private final WebClient webClient;

    public RiotApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<SummonerInfoDto> getSummonerByName(String summonerName) {
        String uri = String.format(
                "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/EUW",
                summonerName
        );

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(SummonerInfoDto.class);
    }

    @Override
    public Mono<List<String>> getMatchHistoryIds(String summonerName) {
        return getSummonerByName(summonerName)
                .flatMap(summoner -> {
                    String puuid = summoner.getPuuid();
                    String uri = String.format(
                            "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=0",
                            puuid
                    );

                    return webClient.get()
                            .uri(uri)
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<List<String>>() {});
                });
    }

    @Override
    public Mono<List<MatchStatsDto>> getStatsForPreviousMatches(String summonerName) {
        return getSummonerByName(summonerName)
                .flatMap(summoner -> {
                    String puuid = summoner.getPuuid();

                    return getMatchHistoryIds(summonerName)
                            .flatMapMany(matchIds -> Flux.fromIterable(matchIds.stream().limit(10).toList()))
                            .delayElements(Duration.ofMillis(250))
                            .flatMap(matchId -> fetchSingleMatchStats(matchId, puuid), 2)
                            .collectList();
                });
    }

    private Mono<MatchStatsDto> fetchSingleMatchStats(String matchId, String puuid) {
        String uri = String.format("https://europe.api.riotgames.com/lol/match/v5/matches/%s", matchId);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(RiotMatchReponse.class)
                .map(matchResponse -> {
                    // filtriraj samo tvog igrača
                    PlayerMatchStatsDto playerStats = matchResponse.getInfo().getPlayerStats()
                            .stream()
                            .filter(p -> p.getPuuid().equalsIgnoreCase(puuid))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Summoner not found in match"));

                    // Create info with basic match informations
                    Info gameInfo = new Info();
                    gameInfo.setGameId(matchResponse.getInfo().getGameId());
                    gameInfo.setGameDuration(matchResponse.getInfo().getGameDuration());
                    gameInfo.setGameMode(matchResponse.getInfo().getGameMode());
                    gameInfo.setMapId(matchResponse.getInfo().getMapId());

                    // Create MatchStatsDto for player with given puuid
                    MatchStatsDto statsDto = new MatchStatsDto();
                    statsDto.setGameInfo(gameInfo);
                    statsDto.setPlayerStats(playerStats);

                    return statsDto;
                });
    }
}
