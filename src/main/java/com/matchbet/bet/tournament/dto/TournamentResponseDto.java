package com.matchbet.bet.tournament.dto;

import com.matchbet.bet.team.dto.TeamDto;
import com.matchbet.bet.user.dto.UserResponseDto;
import java.util.Date;

import java.util.List;

public class TournamentResponseDto {

    private UserResponseDto creator;
    private List<UserResponseDto> users;
    private List<TeamDto> teams;
    private String name;
    private String description;
    private Date createdAt;

    public UserResponseDto getCreator() {
        return creator;
    }

    public void setCreator(UserResponseDto creator) {
        this.creator = creator;
    }

    public List<UserResponseDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponseDto> users) {
        this.users = users;
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDto> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
