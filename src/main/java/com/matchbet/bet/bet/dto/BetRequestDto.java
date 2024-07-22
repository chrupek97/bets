package com.matchbet.bet.bet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetRequestDto {

    @NotNull(message = "Field matchId cannot be null")
    private Long matchId;
    
    @NotNull(message = "Field teamAGoalsScoredPrediction cannot be null")
    private int teamAGoalsScoredPrediction;
    
    @NotBlank(message = "Field teamBGoalsScoredPrediction cannot be empty")
    private int teamBGoalsScoredPrediction;
    
    @NotNull(message = "Field earnedPoints cannot be empty")
    private int earnedPoints;

}
