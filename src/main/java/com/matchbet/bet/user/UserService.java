package com.matchbet.bet.user;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.exceptions.ValidationException;
import com.matchbet.bet.user.dto.UserRequestDto;
import com.matchbet.bet.user.dto.UserResponseDto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::mapToUserResponseDto)
                .toList();
    }

    public UserResponseDto getUser(Long userId) {
        UserEntity userEntity = getUserById(userId);
        return userMapper.mapToUserResponseDto(userEntity);
    }

    public UserResponseDto createUser(UserRequestDto userDto) throws ValidationException {
        validateData(userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setRole("USER");
        userEntity.setEmail(userDto.getEmail());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(userEntity);
        return userMapper.mapToUserResponseDto(userEntity);
    }

    public void deleteById(Long userId) {
        userRepo.deleteById(userId);
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userDto) throws NotFoundException {
        UserEntity userEntity = getUserById(userId);
        userMapper.updateUserEntity(userEntity, userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.mapToUserResponseDto(userRepo.saveAndFlush(userEntity));
    }

    public UserEntity getUserById(Long userId) throws NotFoundException {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Cannot find user with id %d".formatted(userId)));
    }

    public boolean verifyUser() {
        String loggedUsername = getLoggedInUsername();
        return getAllUsers().stream().anyMatch(user -> user.getUsername().equals(loggedUsername));
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication.getName())) {
            throw new NotFoundException("Cannot found user");
        }
        return authentication.getName();
    }

    private void validateData(UserRequestDto userDto) throws ValidationException {
        boolean usernameIsExist = getAllUsers().stream()
                .anyMatch(user -> user.getUsername().equals(userDto.getUsername()));
        boolean emailIsExist = getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equals(userDto.getEmail()));
        if (usernameIsExist) {
            throw new ValidationException("User with username %s already exist".formatted(userDto.getUsername()));
        }

        if (emailIsExist) {
            throw new ValidationException("User with email %s already exist".formatted(userDto.getEmail()));
        }
    }
}
