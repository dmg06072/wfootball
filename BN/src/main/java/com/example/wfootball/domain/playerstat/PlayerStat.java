package com.example.wfootball.domain.playerstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "player_stat")
public class PlayerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RapidAPI player ID
    @Column(nullable = false)
    private Integer externalPlayerId;

    private String playerName;
    private String teamName;

    private Integer leagueId;
    private Integer season;

    private Integer goals;
    private Integer assists;

    public void updateStat(int goals, int assists) {
        this.goals = goals;
        this.assists = assists;
    }
}
