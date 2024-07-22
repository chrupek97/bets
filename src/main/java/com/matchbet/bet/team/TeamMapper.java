package com.matchbet.bet.team;

import com.matchbet.bet.team.dto.TeamDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {

    @Mapping(target = "id", ignore = true)
    public abstract TeamEntity mapToEntity(TeamDto teamDto);

    public abstract TeamDto mapToTeamResponse(TeamEntity teamEntity);
}
