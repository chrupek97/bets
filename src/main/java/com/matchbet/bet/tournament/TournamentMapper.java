package com.matchbet.bet.tournament;

import com.matchbet.bet.tournament.dto.TournamentNewRequestDto;
import com.matchbet.bet.tournament.dto.TournamentResponseDto;
import com.matchbet.bet.tournament.dto.TournamentUpdateRequestDto;
import com.matchbet.bet.user.UserEntity;
import com.matchbet.bet.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TournamentMapper {

    @Autowired
    private UserService userService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "creator", source = "creatorId", qualifiedByName = "getUser")
    public abstract TournamentEntity mapToEntity(TournamentNewRequestDto tournamentDto);

    public abstract TournamentResponseDto mapToTournamentResponseDto(TournamentEntity tournamentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "teams", ignore = true)
    public abstract void updateTournamentEntity(@MappingTarget TournamentEntity tournamentEntity, TournamentUpdateRequestDto tournamentDto);

    @Named("getUser")
    public UserEntity getUser(Long id) throws Exception {
        return userService.getUserById(id);
    }

}
