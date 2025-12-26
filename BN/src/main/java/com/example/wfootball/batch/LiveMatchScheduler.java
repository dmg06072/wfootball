package com.example.wfootball.batch;

import com.example.wfootball.domain.match.MatchService;
import com.example.wfootball.external.apifootball.ApiFootballClient;
import com.example.wfootball.external.apifootball.dto.AFResponse;
import com.example.wfootball.external.apifootball.dto.FixtureResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LiveMatchScheduler {

    private final ApiFootballClient apiFootballClient;
    private final MatchService matchService;

    /**
     * 1분 간격 LIVE 경기 업데이트
     */
    @Scheduled(fixedDelay = 60000)
    public void updateLiveMatches() {

        apiFootballClient.getLiveFixtures()
                .map(json -> apiFootballClient.parse(json, FixtureResponse.class))
                .subscribe(response -> {
                    for (FixtureResponse fr : response.getResponse()) {
                        matchService.upsertFixture(fr);
                    }
                });

        log.info("[LiveMatchScheduler] 라이브 경기 업데이트 완료");
    }
}
