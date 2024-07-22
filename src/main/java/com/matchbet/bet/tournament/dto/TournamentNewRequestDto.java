package com.matchbet.bet.tournament.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Validated
public class TournamentNewRequestDto {

    @NotNull(message = "Field creatorId cannot be null")
    private Long creatorId;
    
    @NotNull(message = "Field teamIds cannot be null")
    private List<Long> teamIds;
    
    @NotBlank(message = "Field name cannot be empty")
    private String name;
    
    @NotBlank(message = "Field description cannot be empty")
    private String description;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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
