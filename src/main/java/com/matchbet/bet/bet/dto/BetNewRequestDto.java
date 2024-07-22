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
public class BetNewRequestDto {

    @NotNull(message = "Field userId cannot be null")
    private Long userId;

    @NotNull(message = "Field matchId cannot be null")
    private Long matchId;

    @NotNull(message = "Field tournamentId cannot be null")
    private Long tournamentId;

    @NotBlank(message = "Field teamAGoalsScoredPrediction cannot be empty")
    private int teamAGoalsScoredPrediction;

    @NotBlank(message = "Field teamBGoalsScoredPrediction cannot be empty")
    private int teamBGoalsScoredPrediction;
}
