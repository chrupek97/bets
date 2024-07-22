package com.matchbet.bet.tournament;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.tournament.dto.TournamentNewRequestDto;
import com.matchbet.bet.tournament.dto.TournamentResponseDto;
import com.matchbet.bet.tournament.dto.TournamentUpdateRequestDto;
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
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<TournamentResponseDto> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public TournamentResponseDto getTournamentResponseById(@PathVariable("id") Long tournamentId) throws NotFoundException {
        return tournamentService.getTorunament(tournamentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentResponseDto createTournament(@RequestBody @Valid TournamentNewRequestDto tournament) {
        return tournamentService.createTournament(tournament);
    }

    @PutMapping("/{id}")
    public TournamentResponseDto updateTournament(@PathVariable("id") Long tournamentId, @RequestBody @Valid TournamentUpdateRequestDto tournament) throws NotFoundException {
        return tournamentService.updateTournament(tournamentId, tournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable("id") Long id) {
        tournamentService.deleteById(id);
    }

}
