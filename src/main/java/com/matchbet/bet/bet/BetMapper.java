package com.matchbet.bet.bet;

import com.matchbet.bet.bet.dto.BetNewRequestDto;
import com.matchbet.bet.bet.dto.BetRequestDto;
import com.matchbet.bet.bet.dto.BetResponseDto;
import com.matchbet.bet.match.MatchEntity;
import com.matchbet.bet.match.MatchService;
import com.matchbet.bet.tournament.TournamentEntity;
import com.matchbet.bet.tournament.TournamentService;
import com.matchbet.bet.user.UserEntity;
import com.matchbet.bet.user.UserService;
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
public abstract class BetMapper {

    private MatchService matchService;
    private UserService userService;
    private TournamentService tournamentService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "getUser")
    @Mapping(target = "match", source = "matchId", qualifiedByName = "getMatch")
    @Mapping(target = "tournament", source = "tournamentId", qualifiedByName = "getTournament")
    @Mapping(target = "earnedPoints", ignore = true)
    public abstract BetEntity mapToEntity(BetNewRequestDto dto);

    public abstract BetResponseDto mapToBetResponseDto(BetEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "match", source = "matchId", qualifiedByName = "getMatch")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tournament", ignore = true)
    public abstract void updateBet(@MappingTarget BetEntity betEntity, BetRequestDto betDto);

    @Named("getMatch")
    public MatchEntity getMatch(Long id) throws Exception {
        return matchService.getMatchById(id);
    }

    @Named("getUser")
    public UserEntity getUser(Long id) throws Exception {
        return userService.getUserById(id);
    }

    @Named("getTournament")
    public TournamentEntity getTournament(Long id) throws Exception {
        return tournamentService.getTournamentById(id);
    }
}
