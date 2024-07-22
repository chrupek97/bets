package com.matchbet.bet.user;

import com.matchbet.bet.user.dto.UserRequestDto;
import com.matchbet.bet.user.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "role", ignore = true)
    public abstract UserEntity mapToUserEntity(UserRequestDto userDto);

    public abstract UserResponseDto mapToUserResponseDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "bets", ignore = true)
    @Mapping(target = "role", ignore = true)
    public abstract void updateUserEntity(@MappingTarget UserEntity userEntity, UserRequestDto userDto);
}
