package com.matchbet.bet.match.dto;

import com.matchbet.bet.team.dto.TeamDto;
import com.matchbet.bet.tournament.dto.TournamentResponseDto;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResponseDto {

    private TournamentResponseDto tournament;
    private TeamDto teamA;
    private TeamDto teamB;
    private Date matchDate;
    private int teamAGoalsScored;
    private int teamBGoalsScored;

}
