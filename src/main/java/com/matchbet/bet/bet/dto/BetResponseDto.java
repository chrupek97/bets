package com.matchbet.bet.bet.dto;

import com.matchbet.bet.match.dto.MatchResponseDto;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetResponseDto {

    private MatchResponseDto match;
    private int teamAGoalsScoredPrediction;
    private int teamBGoalsScoredPrediction;
    private int earnedPoints;
}
