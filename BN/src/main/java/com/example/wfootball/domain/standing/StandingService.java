package com.example.wfootball.domain.standing;

import com.example.wfootball.external.apifootball.dto.StandingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StandingService {

    private final StandingRepository standingRepository;

    @Transactional
    public void upsertStanding(StandingResponse.Standing dto, int leagueId, int season) {

        standingRepository.findByExternalTeamId(dto.getTeam().getId())
                .map(st -> {
                    st.updateStanding(
                            dto.getRank(),
                            dto.getPoints(),
                            dto.getGoalsDiff(),
                            dto.getAll().getPlayed(),
                            dto.getAll().getWin(),
                            dto.getAll().getDraw(),
                            dto.getAll().getLose()
                    );
                    return st;
                })
                .orElseGet(() ->
                        standingRepository.save(
                                Standing.builder()
                                        .externalTeamId(dto.getTeam().getId())
                                        .teamName(dto.getTeam().getName())
                                        .leagueId(leagueId)
                                        .season(season)
                                        .rank(dto.getRank())
                                        .points(dto.getPoints())
                                        .goalsDiff(dto.getGoalsDiff())
                                        .played(dto.getAll().getPlayed())
                                        .win(dto.getAll().getWin())
                                        .draw(dto.getAll().getDraw())
                                        .lose(dto.getAll().getLose())
                                        .build()
                        )
                );
    }
}
