package com.example.wfootball.domain.playerstat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerStatRepository extends JpaRepository<PlayerStat, Long> {

    Optional<PlayerStat> findByExternalPlayerId(Integer externalPlayerId);
}
