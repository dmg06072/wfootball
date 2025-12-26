package com.example.wfootball.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<com.example.wfootball.domain.team.Team, Long> {
    Optional<com.example.wfootball.domain.team.Team> findByExternalId(Integer externalId);
}
