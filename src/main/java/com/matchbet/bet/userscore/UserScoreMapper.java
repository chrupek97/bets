package com.matchbet.bet.userscore;

import com.matchbet.bet.tournament.TournamentEntity;
import com.matchbet.bet.tournament.TournamentService;
import com.matchbet.bet.user.UserEntity;
import com.matchbet.bet.user.UserService;
import com.matchbet.bet.userscore.dto.UserScoreRequestDto;
import com.matchbet.bet.userscore.dto.UserScoreResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserScoreMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "getUser")
    @Mapping(target = "tournament", source = "tournamentId", qualifiedByName = "getTournament")
    public abstract UserScoreEntity mapToUserScoreEntity(UserScoreRequestDto userScoreDto);

    public abstract UserScoreResponseDto mapToUserScoreResponseDto(UserScoreEntity userScoreEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tournament", ignore = true)
    public abstract void updateUserScoreEntity(@MappingTarget UserScoreEntity userScoreEntity, UserScoreRequestDto userScoreRequestDto);

    @Named("getUser")
    public UserEntity getUser(Long id) throws Exception {
        return userService.getUserById(id);
    }

    @Named("getTournament")
    public TournamentEntity getTournament(Long id) throws Exception {
        return tournamentService.getTournamentById(id);
    }
}
