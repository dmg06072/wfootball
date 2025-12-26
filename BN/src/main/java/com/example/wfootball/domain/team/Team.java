package com.example.wfootball.domain.team;

import com.example.wfootball.domain.league.League;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private Integer externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @Column(nullable = false)
    private String name;

    @Column(length = 10)
    private String code; // MUN, RMA 등

    private String country;

    @Column(name = "logo_url")
    private String logoUrl;
}
