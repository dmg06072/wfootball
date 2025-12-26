package com.example.wfootball.domain.match;

import com.example.wfootball.domain.league.League;
import com.example.wfootball.domain.league.LeagueRepository;
import com.example.wfootball.domain.team.Team;
import com.example.wfootball.domain.team.TeamRepository;
import com.example.wfootball.external.apifootball.FixtureMapper;
import com.example.wfootball.external.apifootball.dto.FixtureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final FixtureMapper fixtureMapper;

    @Transactional
    public void upsertFixture(FixtureResponse dto) {

        // 1. League upsert
        League league = leagueRepository.findByExternalId(dto.getLeague().getId())
                .orElseGet(() -> leagueRepository.save(fixtureMapper.toLeague(dto)));

        // 2. Teams upsert
        Team home = teamRepository.findByExternalId(dto.getTeams().getHome().getId())
                .orElseGet(() -> teamRepository.save(
                        fixtureMapper.toTeam(dto.getTeams().getHome())
                ));

        Team away = teamRepository.findByExternalId(dto.getTeams().getAway().getId())
                .orElseGet(() -> teamRepository.save(
                        fixtureMapper.toTeam(dto.getTeams().getAway())
                ));

        // 3. Match upsert
        matchRepository.findByExternalId(dto.getFixture().getId())
                .map(match -> {
                    match.updateMatch(
                            dto.getGoals().getHome(),
                            dto.getGoals().getAway(),
                            dto.getFixture().getStatus().getShortCode(),
                            dto.getFixture().getStatus().getLongCode()
                    );
                    return match;
                })
                .orElseGet(() ->
                        matchRepository.save(
                                fixtureMapper.toMatch(dto, league, home, away)
                        )
                );
    }
}
