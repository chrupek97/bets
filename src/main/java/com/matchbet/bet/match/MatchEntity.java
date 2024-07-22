package com.matchbet.bet.match;

import com.matchbet.bet.bet.BetEntity;
import com.matchbet.bet.team.TeamEntity;
import com.matchbet.bet.tournament.TournamentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TournamentEntity tournament;

    @OneToMany(mappedBy = "match")
    private List<BetEntity> bets;

    @ManyToOne
    private TeamEntity teamA;

    @ManyToOne
    private TeamEntity teamB;

    private Date matchDate;
    private int teamAGoalsScored;
    private int teamBGoalsScored;

}
