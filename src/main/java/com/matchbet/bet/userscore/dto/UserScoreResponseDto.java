package com.matchbet.bet.userscore.dto;

import com.matchbet.bet.tournament.dto.TournamentResponseDto;
import com.matchbet.bet.user.dto.UserResponseDto;

public class UserScoreResponseDto {
    
    private UserResponseDto user;
    private TournamentResponseDto tournament;
    private int totalPoints;

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public TournamentResponseDto getTournament() {
        return tournament;
    }

    public void setTournament(TournamentResponseDto tournament) {
        this.tournament = tournament;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    
}
