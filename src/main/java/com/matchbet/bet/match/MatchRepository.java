package com.matchbet.bet.match;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    List<MatchEntity> findByTournament_id(Long id);
}
