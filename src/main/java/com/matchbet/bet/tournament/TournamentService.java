package com.matchbet.bet.tournament;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.team.TeamService;
import com.matchbet.bet.tournament.dto.TournamentNewRequestDto;
import com.matchbet.bet.tournament.dto.TournamentResponseDto;
import com.matchbet.bet.tournament.dto.TournamentUpdateRequestDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepo;

    @Autowired
    private TournamentMapper tournamentMapper;

    public List<TournamentResponseDto> getAllTournaments() {
        return tournamentRepo.findAll().stream()
                .map(tournamentMapper::mapToTournamentResponseDto)
                .toList();
    }

    public TournamentResponseDto getTorunament(Long tournamentId) throws NotFoundException {
        TournamentEntity tournamentEntity = getTournamentById(tournamentId);
        return tournamentMapper.mapToTournamentResponseDto(tournamentEntity);
    }

    public TournamentResponseDto createTournament(TournamentNewRequestDto tournamentDto) {
        TournamentEntity tournamentEntity = tournamentRepo.save(tournamentMapper.mapToEntity(tournamentDto));
        return tournamentMapper.mapToTournamentResponseDto(tournamentEntity);
    }

    public void deleteById(Long tournamentId) {
        tournamentRepo.deleteById(tournamentId);
    }

    public TournamentResponseDto updateTournament(Long tournamentId, TournamentUpdateRequestDto tournamentDto) throws NotFoundException {
        TournamentEntity tournamentEntity = getTournamentById(tournamentId);
        tournamentMapper.updateTournamentEntity(tournamentEntity, tournamentDto);
        return tournamentMapper.mapToTournamentResponseDto(tournamentRepo.saveAndFlush(tournamentEntity));
    }

    public TournamentEntity getTournamentById(Long tournamentId) throws NotFoundException {
        return tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Cannot find tournament with id %d".formatted(tournamentId)));
    }
}
