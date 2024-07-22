package com.matchbet.bet.bet;

import com.matchbet.bet.bet.dto.BetNewRequestDto;
import com.matchbet.bet.bet.dto.BetRequestDto;
import com.matchbet.bet.bet.dto.BetResponseDto;
import com.matchbet.bet.exceptions.NotFoundException;
import jakarta.validation.Valid;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/bets")
@AllArgsConstructor
public class BetController {

    @Autowired
    private BetService betService;
    
    @GetMapping("/{id}")
    public BetResponseDto getBetById(@PathVariable("id") Long id) throws NotFoundException {
        return betService.getBet(id);
    }

    @GetMapping("/user/{id}")
    public List<BetResponseDto> getBetsByUserId(@PathVariable("id") Long id) {
        return betService.getBetsByUserId(id);
    }

    @GetMapping("/tournament/{id}")
    public List<BetResponseDto> getBetsByTournamentId(@PathVariable("id") Long id) {
        return betService.getBetsByTournamentId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BetResponseDto createBet(@RequestBody @Validated BetNewRequestDto betDto) {
        return betService.createBet(betDto);
    }

    @PutMapping("/{id}")
    public BetResponseDto updateBet(@PathVariable("id") Long betId, @RequestBody @Validated BetRequestDto betDto) throws NotFoundException {
        return betService.updateBet(betId, betDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBet(@PathVariable("id") Long id) {
        betService.deleteBet(id);
    }
}
