package com.matchbet.bet.userscore;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.userscore.dto.UserScoreRequestDto;
import com.matchbet.bet.userscore.dto.UserScoreResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/user_scores")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    @GetMapping("/{id}")
    public UserScoreResponseDto getUserScoreById(@PathVariable("id") Long userScoreId) throws NotFoundException {
        return userScoreService.getUserScore(userScoreId);
    }

    @GetMapping("/tournament/{id}")
    public List<UserScoreResponseDto> getUserScoresByTournamentId(@PathVariable("id") Long tournamentId) {
        return userScoreService.getUserScoresByTournamentId(tournamentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserScoreResponseDto createUserScore(@RequestBody @Valid UserScoreRequestDto userScoreDto) {
        return userScoreService.createUserScore(userScoreDto);
    }

    @PutMapping("/{id}")
    public UserScoreResponseDto updateUserScore(@PathVariable("id") Long userScoreId, @RequestBody @Valid UserScoreRequestDto userScoreDto) throws NotFoundException {
        return userScoreService.updateUserScore(userScoreId, userScoreDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserScore(@PathVariable("id") Long userScoreId) {
        userScoreService.deleteById(userScoreId);
    }
}
