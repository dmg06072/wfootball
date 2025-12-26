package com.example.wfootball.batch;

import com.example.wfootball.domain.playerstat.PlayerStatService;
import com.example.wfootball.external.apifootball.ApiFootballClient;
import com.example.wfootball.external.apifootball.dto.PlayerStatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerStatScheduler {

    private final ApiFootballClient client;
    private final PlayerStatService playerStatService;

    @Scheduled(cron = "0 10 4 * * *", zone = "Asia/Seoul")
    public void updatePlayerStats() {

        int leagueId = 39;
        int season = 2024;

        client.getTopScorers(leagueId, season)
                .map(json -> client.parse(json, PlayerStatResponse.class))
                .subscribe(res -> {
                    for (PlayerStatResponse ps : res.getResponse()) {
                        playerStatService.upsert(ps, leagueId, season);
                    }
                });

        log.info("[PlayerStatScheduler] 선수 스탯 업데이트 완료");
    }
}
