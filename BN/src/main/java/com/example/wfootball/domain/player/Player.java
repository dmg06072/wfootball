package com.example.wfootball.domain.player;

import com.example.wfootball.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private Integer externalId;

    @Column(nullable = false)
    private String name;

    private Integer age;

    private String nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(length = 20)
    private String position; // F, M, D, G

    @Column(name = "photo_url")
    private String photoUrl;
}
