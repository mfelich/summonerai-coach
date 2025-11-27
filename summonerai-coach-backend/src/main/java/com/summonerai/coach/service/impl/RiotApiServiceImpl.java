package com.summonerai.coach.service.impl;

import com.summonerai.coach.dto.MatchStatsDto;
import com.summonerai.coach.dto.analysis.PlayerAnalysisRequestDto;
import com.summonerai.coach.dto.summoner.SummonerInfoDto;
import com.summonerai.coach.dto.riot.RiotInfoDto;
import com.summonerai.coach.dto.summoner.SummonerMatchStatsDto;
import com.summonerai.coach.dto.riot.RiotMatchDto;
import com.summonerai.coach.exception.MatchHistoryNotFoundException;
import com.summonerai.coach.exception.MatchStatsNotFoundException;
import com.summonerai.coach.exception.RiotApiServerErrorException;
import com.summonerai.coach.exception.SummonerNotFoundException;
import com.summonerai.coach.service.RiotApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiotApiServiceImpl implements RiotApiService {

    private final WebClient webClient;

    private Mono<SummonerInfoDto> getSummonerByName(PlayerAnalysisRequestDto request) {

        log.info("Starting fetch data for summoner='{}', region='{}'",
                request.getSummonerName(),
                request.getRegion());

        String region = request.getRegion().toString().toLowerCase();
        String summonerName = request.getSummonerName();

        String uri = String.format(
                "https://%s.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/EUW",
                region,
                summonerName
        );

        log.debug("Riot API URI: {}", uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new SummonerNotFoundException("Summoner not find with given name.")))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RiotApiServerErrorException("Riot API service error.")))
                .bodyToMono(SummonerInfoDto.class);
    }

    private Mono<List<String>> getMatchHistoryIds(PlayerAnalysisRequestDto request) {
        return getSummonerByName(request)
                .flatMap(summoner -> {
                    String puuid = summoner.getPuuid();
                    String uri = String.format(
                            "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=0",
                            puuid
                    );

                    log.debug("Riot API MatchHistory URI: {}", uri);

                    return webClient.get()
                            .uri(uri)
                            .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError, response ->
                                    Mono.error(new MatchHistoryNotFoundException("Match history not found for this summoner.")))
                            .onStatus(HttpStatusCode::is5xxServerError, response ->
                                    Mono.error(new RiotApiServerErrorException("Riot API service error.")))
                            .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                            .flatMap(list -> {
                                if (list == null || list.isEmpty()) {
                                    return Mono.error(new MatchHistoryNotFoundException("Match history not found for this summoner."));
                                }

                                log.info("Match history ids found for summoner {}, total matches found {}",request.getSummonerName(),list.size());
                                return Mono.just(list);
                            });
                });
    }

    private Mono<MatchStatsDto> fetchSingleMatchStats(String matchId, String puuid) {
        String uri = String.format("https://europe.api.riotgames.com/lol/match/v5/matches/%s", matchId);
        log.debug("Riot API MatchStats URI: {}", uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new MatchStatsNotFoundException("Match stats not found.")))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RiotApiServerErrorException("Riot API service error.")))
                .bodyToMono(RiotMatchDto.class)
                .map(matchResponse -> {
                    SummonerMatchStatsDto playerStats = matchResponse.getRiotInfoDto().getPlayerStats()
                            .stream()
                            .filter(p -> p.getPuuid().equalsIgnoreCase(puuid))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Summoner not found in match"));

                    RiotInfoDto gameRiotInfoDto = new RiotInfoDto();
                    gameRiotInfoDto.setGameId(matchResponse.getRiotInfoDto().getGameId());
                    gameRiotInfoDto.setGameDuration(matchResponse.getRiotInfoDto().getGameDuration());
                    gameRiotInfoDto.setGameMode(matchResponse.getRiotInfoDto().getGameMode());
                    gameRiotInfoDto.setMapId(matchResponse.getRiotInfoDto().getMapId());

                    MatchStatsDto stats = new MatchStatsDto();
                    stats.setGameRiotInfoDto(gameRiotInfoDto);
                    stats.setPlayerStats(playerStats);

                    return stats;
                });
    }

    @Override
    public Mono<List<MatchStatsDto>> getStatsForPreviousMatches(PlayerAnalysisRequestDto request) {
        log.info("Fetching previous match stats for summoner '{}'", request.getSummonerName());

        return getSummonerByName(request)
                .flatMap(summoner -> {
                    String puuid = summoner.getPuuid();

                    Mono<List<MatchStatsDto>> matchStats = getMatchHistoryIds(request)
                            .flatMapMany(matchIds -> Flux.fromIterable(matchIds.stream().limit(10).toList()))
                            .delayElements(Duration.ofMillis(250))
                            .flatMap(matchId -> fetchSingleMatchStats(matchId, puuid), 2)
                            .collectList();

                    return matchStats;
                });
    }
}
