package com.example.wfootball.external.apifootball;

import com.example.wfootball.domain.league.League;
import com.example.wfootball.domain.match.Match;
import com.example.wfootball.domain.team.Team;
import com.example.wfootball.external.apifootball.dto.FixtureResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixtureMapper {

    public League toLeague(FixtureResponse dto) {
        FixtureResponse.League l = dto.getLeague();
        return League.builder()
                .externalId(l.getId())
                .name(l.getName())
                .season(l.getSeason())
                .country("Unknown") // 실제 API에 있음 (간단화)
                .type("League")
                .build();
    }

    public Team toTeam(FixtureResponse.Teams.TeamInfo t) {
        return Team.builder()
                .externalId(t.getId())
                .name(t.getName())
                .build();
    }

    public Match toMatch(FixtureResponse dto, League league, Team home, Team away) {

        return Match.builder()
                .externalId(dto.getFixture().getId())
                .league(league)
                .season(dto.getLeague().getSeason())
                .homeTeam(home)
                .awayTeam(away)
                .kickoffAt(LocalDateTime.parse(dto.getFixture().getDate()))
                .status(dto.getFixture().getStatus().getShortCode())
                .statusLong(dto.getFixture().getStatus().getLongCode())
                .venue(dto.getFixture().getVenue().getName())
                .scoreHome(dto.getGoals().getHome())
                .scoreAway(dto.getGoals().getAway())
                .build();
    }
}
