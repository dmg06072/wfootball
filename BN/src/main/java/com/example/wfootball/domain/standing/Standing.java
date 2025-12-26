package com.example.wfootball.domain.standing;

import com.example.wfootball.domain.league.League;
import com.example.wfootball.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "standing")
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer externalTeamId;
    private String teamName;

    private Integer leagueId;
    private Integer season;

    private Integer rank;
    private Integer points;
    private Integer played;
    private Integer win;
    private Integer draw;
    private Integer loss;

    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDiff;

    private String last5;

    public void updateStanding(int rank, int points, int goalDiff, int played,
                               int win, int draw, int loss) {
        this.rank = rank;
        this.points = points;
        this.goalDiff = goalDiff;
        this.played = played;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
    }
}

