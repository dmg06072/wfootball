package com.example.wfootball.batch;

import com.example.wfootball.domain.standing.StandingService;
import com.example.wfootball.external.apifootball.ApiFootballClient;
import com.example.wfootball.external.apifootball.dto.StandingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StandingScheduler {

    private final ApiFootballClient client;
    private final StandingService standingService;

    @Scheduled(cron = "0 5 4 * * *", zone = "Asia/Seoul")
    public void updateStandings() {

        int leagueId = 39;
        int season = 2024;

        client.getStandings(leagueId, season)
                .map(json -> client.parse(json, StandingResponse.class))
                .subscribe(res -> {
                    for (StandingResponse.Standing[] arr : res.getResponse().get(0).getLeague().getStandings()) {
                        for (StandingResponse.Standing s : arr) {
                            standingService.upsertStanding(s, leagueId, season);
                        }
                    }
                });

        log.info("[StandingScheduler] 순위 업데이트 완료");
    }
}
