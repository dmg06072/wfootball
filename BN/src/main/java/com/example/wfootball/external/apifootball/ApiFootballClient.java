package com.example.wfootball.external.apifootball;

import com.example.wfootball.external.apifootball.dto.AFResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;


@Component
@RequiredArgsConstructor
public class ApiFootballClient {

    private final WebClient webClient;
    private final ApiFootballConfig config;

    // 공통 GET 요청
    private Mono<String> get(String uri) {
        return webClient.get()
                .uri(config.getBaseUrl() + uri)
                .header("x-rapidapi-key", config.getApiKey())
                .header("x-rapidapi-host", config.getHost())
                .retrieve()
                .bodyToMono(String.class);
    }

    /** 1) 특정 날짜 경기 일정 */
    public Mono<String> getFixturesByDate(String date) {
        return get("/fixtures?date=" + date);
    }

    /** 2) 리그/시즌 기준 경기 일정 */
    public Mono<String> getFixturesByLeague(int leagueId, int season) {
        return get("/fixtures?league=" + leagueId + "&season=" + season);
    }

    /** 3) 리그 순위 */
    public Mono<String> getStandings(int leagueId, int season) {
        return get("/standings?league=" + leagueId + "&season=" + season);
    }

    /** 4) 탑 득점자 */
    public Mono<String> getTopScorers(int leagueId, int season) {
        return get("/players/topscorers?league=" + leagueId + "&season=" + season);
    }

    /** 5) 탑 어시스트 */
    public Mono<String> getTopAssists(int leagueId, int season) {
        return get("/players/topassists?league=" + leagueId + "&season=" + season);
    }

    /** 6) LIVE 경기 */
    public Mono<String> getLiveFixtures() {
        return get("/fixtures?live=all");
    }

    @SneakyThrows
    public <T> AFResponse<T> parse(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                json,
                mapper.getTypeFactory().constructParametricType(AFResponse.class, clazz)
        );
    }

}
