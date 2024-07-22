package com.matchbet.bet.userscore;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScoreRepository extends JpaRepository<UserScoreEntity, Long> {

    List<UserScoreEntity> findByTournament_id(Long id);
}
