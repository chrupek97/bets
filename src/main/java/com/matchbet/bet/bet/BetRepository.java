package com.matchbet.bet.bet;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<BetEntity, Long> {

    List<BetEntity> findByUser_Id(Long id);

    List<BetEntity> findByTournament_Id(Long id);
    
    List<BetEntity> findByMatch_Id(Long id);
}
