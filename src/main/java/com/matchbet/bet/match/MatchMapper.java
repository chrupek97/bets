package com.matchbet.bet.match;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.match.dto.MatchRequestDto;
import com.matchbet.bet.match.dto.MatchResponseDto;
import com.matchbet.bet.match.dto.MatchUpdateScoreRequestDto;
import com.matchbet.bet.team.TeamEntity;
import com.matchbet.bet.team.TeamService;
import com.matchbet.bet.tournament.TournamentEntity;
import com.matchbet.bet.tournament.TournamentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@AllArgsConstructor
@NoArgsConstructor
public abstract class MatchMapper {

    private TeamService teamService;
    private TournamentService tournamentService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamAGoalsScored", ignore = true)
    @Mapping(target = "teamBGoalsScored", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "teamA", source = "teamAId", qualifiedByName = "getTeam")
    @Mapping(target = "teamB", source = "teamBId", qualifiedByName = "getTeam")
    @Mapping(target = "tournament", source = "tournamentId", qualifiedByName = "getTournament")
    public abstract MatchEntity mapToEntity(MatchRequestDto matchDto);

    public abstract MatchResponseDto mapToMatchResponseDto(MatchEntity matchEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tournament", ignore = true)
    @Mapping(target = "teamAGoalsScored", ignore = true)
    @Mapping(target = "teamBGoalsScored", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "teamA", source = "teamAId", qualifiedByName = "getTeam")
    @Mapping(target = "teamB", source = "teamBId", qualifiedByName = "getTeam")
    public abstract void updateMatch(@MappingTarget MatchEntity matchEntity, MatchRequestDto matchDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tournament", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "teamA", ignore = true)
    @Mapping(target = "teamB", ignore = true)
    @Mapping(target = "matchDate", ignore = true)
    public abstract void upradeMatchScore(@MappingTarget MatchEntity matchEntity, MatchUpdateScoreRequestDto matchUpdateScoreDto);

    @Named("getTeam")
    public TeamEntity getTeam(Long id) throws NotFoundException {
        return teamService.getTeamById(id);
    }

    @Named("getTournament")
    public TournamentEntity getTournament(Long id) throws NotFoundException {
        return tournamentService.getTournamentById(id);
    }
}
