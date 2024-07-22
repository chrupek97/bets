package com.matchbet.bet.match;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.match.dto.MatchRequestDto;
import com.matchbet.bet.match.dto.MatchResponseDto;
import jakarta.validation.Valid;
import java.util.List;

import lombok.AllArgsConstructor;
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
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @GetMapping("/{id}")
    public MatchResponseDto getMatchById(@PathVariable("id") Long id) throws NotFoundException {
        return matchService.getMatch(id);
    }

    @GetMapping("/tournament/{id}")
    public List<MatchResponseDto> getMatchesByTournamentId(@PathVariable("id") Long id) {
        return matchService.getMatchesByTournamentId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchResponseDto createMatch(@RequestBody @Valid MatchRequestDto matchDto) {
        return matchService.createMatch(matchDto);
    }

    @PutMapping("/{id}")
    public MatchResponseDto updateMatch(@PathVariable("id") Long matchId, @RequestBody @Valid MatchRequestDto matchDto) throws NotFoundException {
        return matchService.updateMatch(matchId, matchDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable("id") Long id) {
        matchService.deleteMatch(id);
    }
}
