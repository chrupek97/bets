package com.matchbet.bet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchbet.bet.user.UserController;
import com.matchbet.bet.user.UserService;
import com.matchbet.bet.user.dto.UserResponseDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserResponseDto userResponseDto;

    @BeforeEach
    public void setup() {
        userMapper = new UserMapperImpl();
        userService = new UserService(userRepository, userMapper, passwordEncoder);
        userResponseDto = UserResponseDto.builder().username("mat").email("mat@mat.pl").build();
    }

//    @Test
//    public void UserController_CreateUser_ReturnCreated() throws Exception {
//        given(userService.createUser(ArgumentMatchers.any()))
//                .willAnswer(invocation -> invocation.getArgument(0));
//
//        ResultActions response = mockMvc.perform(post("api/users/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userResponseDto)));
//
//        response.andExpect(MockMvcResultMatchers.status().isCreated());
//    }

}
