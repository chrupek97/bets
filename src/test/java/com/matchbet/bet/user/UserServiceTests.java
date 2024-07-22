package com.matchbet.bet.user;

import com.matchbet.bet.user.UserService;
import com.matchbet.bet.user.dto.UserRequestDto;
import com.matchbet.bet.user.dto.UserResponseDto;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() {
        userMapper = new UserMapperImpl();
        userService = new UserService(userRepository, userMapper, passwordEncoder);
        userEntity = createUserEntity();
    }

    @Test
    public void UserService_CreateUser_ReturnUserResponseDto() throws Exception {
        UserRequestDto userRequest = new UserRequestDto("test", "123", "test@test.pl");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        UserResponseDto savedUser = userService.createUser(userRequest);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void UserService_GetUser_ReturnUserResponseDto() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        UserResponseDto returnedUser = userService.getUser(1L);

        Assertions.assertThat(returnedUser).isNotNull();
        Assertions.assertThat(returnedUser.getUsername()).isNotNull();
    }

    @Test
    public void UserService_GetAllUsers_ReturnListOfUserResponseDto() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity, userEntity));
        List<UserResponseDto> returnedUsers = userService.getAllUsers();

        Assertions.assertThat(returnedUsers.size()).isEqualTo(2);
        Assertions.assertThat(returnedUsers).isNotEmpty();
    }

    @Test
    public void UserService_UpdateUser_ReturnUserResponseDto() {
        UserRequestDto userRequest = new UserRequestDto("testowy", "123", "test@test.pl");

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        UserResponseDto savedUser = userService.updateUser(1L, userRequest);

        Assertions.assertThat(savedUser).isNotNull();
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@test.pl");
        userEntity.setUsername("test");
        userEntity.setPassword("123");

        return userEntity;
    }
}
