package com.example.wfootball.domain.match;

import com.example.wfootball.domain.league.League;
import com.example.wfootball.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_table") // match 예약어 충돌 피하기 위해
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private Integer externalId; // API-Football fixture ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @Column(nullable = false)
    private Integer season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name = "kickoff_at", nullable = false)
    private LocalDateTime kickoffAt;

    @Column(nullable = false)
    private String status; // SCHEDULED, LIVE, FINISHED 등

    @Column(name = "status_long")
    private String statusLong;

    private String venue;

    private String round;

    @Column(name = "score_home")
    private Integer scoreHome;

    @Column(name = "score_away")
    private Integer scoreAway;

    @Column(name = "halftime_home")
    private Integer halftimeHome;

    @Column(name = "halftime_away")
    private Integer halftimeAway;

    @Column(name = "fulltime_home")
    private Integer fulltimeHome;

    @Column(name = "fulltime_away")
    private Integer fulltimeAway;

    @Column(name = "extra_home")
    private Integer extraHome;

    @Column(name = "extra_away")
    private Integer extraAway;

    @Column(name = "penalty_home")
    private Integer penaltyHome;

    @Column(name = "penalty_away")
    private Integer penaltyAway;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void updateMatch(Integer homeScore, Integer awayScore, String status, String statusLong) {
        this.scoreHome = homeScore;
        this.scoreAway = awayScore;
        this.status = status;
        this.statusLong = statusLong;
    }

}

