package com.matchbet.bet.match.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchUpdateScoreRequestDto {

    @NotBlank(message = "Field teamAGoalsScored cannot be empty")
    @Positive
    private int teamAGoalsScored;

    @NotBlank(message = "Field teamBGoalsScored cannot be empty")
    @Positive
    private int teamBGoalsScored;
}
