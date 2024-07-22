package com.matchbet.bet.tournament.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Validated
public class TournamentUpdateRequestDto {

    @NotNull(message = "Field userIds cannot be null")
    private List<Long> userIds;

    @NotNull(message = "Field teamIds cannot be null")
    private List<Long> teamIds;

    @NotBlank(message = "Field name cannot be empty")
    private String name;

    @NotBlank(message = "Field name cannot be empty")
    private String description;
    
    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
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
}
