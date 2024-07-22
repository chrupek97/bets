package com.matchbet.bet.user;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.user.dto.UserRequestDto;
import com.matchbet.bet.user.dto.UserResponseDto;
import java.security.Principal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("@userService.verifyUser()")
    public UserResponseDto getUserById(@PathVariable("id") Long userId) throws NotFoundException {
        return userService.getUser(userId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody @Validated UserRequestDto userDto) throws NotFoundException {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("@userService.verifyUser()")
    public UserResponseDto updateUser(@PathVariable("id") Long userId, @RequestBody @Validated UserRequestDto userDto) throws NotFoundException {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteById(userId);
    }

}
