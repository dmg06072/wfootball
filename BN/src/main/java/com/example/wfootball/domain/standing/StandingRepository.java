package com.example.wfootball.domain.standing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StandingRepository extends JpaRepository<Standing, Long> {

    Optional<Standing> findByExternalTeamId(Integer externalTeamId);
}
