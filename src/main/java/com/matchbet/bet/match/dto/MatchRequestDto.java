package com.matchbet.bet.match.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

import lombok.*;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRequestDto {

    @NotNull(message = "Field tournamentId cannot be null")
    private Long tournamentId;

    @NotNull(message = "Field teamAId cannot be null")
    private Long teamAId;

    @NotNull(message = "Field teamBId cannot be null")
    private Long teamBId;

    @NotNull(message = "Field matchDate cannot be empty")
    private Date matchDate;
}
