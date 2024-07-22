package com.matchbet.bet.userscore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserScoreRequestDto {

    @NotNull(message = "Field tournamentId cannot be null")
    private Long tournamentId;
    
    @NotNull(message = "Field userId cannot be null")
    private Long userId;
    
    @NotBlank(message = "Field totalPoints cannot be empty")
    @Positive
    private int totalPoints;

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
