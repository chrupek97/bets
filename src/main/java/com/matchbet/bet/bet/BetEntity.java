package com.matchbet.bet.bet;

import com.matchbet.bet.match.MatchEntity;
import com.matchbet.bet.tournament.TournamentEntity;
import com.matchbet.bet.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "bets")
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private MatchEntity match;

    @ManyToOne
    private TournamentEntity tournament;

    private int teamAGoalsScoredPrediction;
    private int teamBGoalsScoredPrediction;
    private int earnedPoints;
}
