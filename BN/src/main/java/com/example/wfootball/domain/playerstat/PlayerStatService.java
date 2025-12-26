package com.example.wfootball.domain.playerstat;

import com.example.wfootball.external.apifootball.dto.PlayerStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerStatService {

    private final PlayerStatRepository playerStatRepository;

    @Transactional
    public void upsert(PlayerStatResponse dto, int leagueId, int season) {

        var stat = dto.getStatistics()[0];   // 배열 구조 맞음

        playerStatRepository.findByExternalPlayerId(dto.getPlayer().getId())
                .map(ps -> {
                    ps.updateStat(
                            stat.getGoals().getTotal(),
                            stat.getGoals().getAssists()
                    );
                    return ps;
                })
                .orElseGet(() ->
                        playerStatRepository.save(
                                PlayerStat.builder()
                                        .externalPlayerId(dto.getPlayer().getId())
                                        .playerName(dto.getPlayer().getName())
                                        .teamName(stat.getTeam().getName())
                                        .leagueId(leagueId)
                                        .season(season)
                                        .goals(stat.getGoals().getTotal())
                                        .assists(stat.getGoals().getAssists())
                                        .build()
                        )
                );
    }
}
