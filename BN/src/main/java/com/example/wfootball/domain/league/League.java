package com.example.wfootball.domain.league;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private Integer externalId; // API-Football League ID

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(nullable = false)
    private String type; // League / Cup

    @Column(nullable = false)
    private Integer season;
}
