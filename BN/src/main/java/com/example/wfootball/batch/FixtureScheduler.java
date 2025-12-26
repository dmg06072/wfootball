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
public class FixtureScheduler {

    private final ApiFootballClient apiFootballClient;
    private final MatchService matchService;

    /**
     * 매일 새벽 4시: 오늘 + 내일 경기 일정 업데이트
     */
    @Scheduled(cron = "0 0 4 * * *", zone = "Asia/Seoul")
    public void updateDailyFixtures() {

        log.info("[FixtureScheduler] 매일 일정 업데이트 시작");

        int leagueId = 39;   // EPL 예시
        int season = 2024;   // 2024 시즌 예시

        apiFootballClient.getFixturesByLeague(leagueId, season)
                .map(json -> apiFootballClient.parse(json, FixtureResponse.class)) // 파싱 (아래 parse 메서드 생성)
                .subscribe(response -> {
                    for (FixtureResponse fr : response.getResponse()) {
                        matchService.upsertFixture(fr);
                    }
                });

        log.info("[FixtureScheduler] 일정 업데이트 완료");
    }
}
