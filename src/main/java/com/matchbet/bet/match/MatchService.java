package com.matchbet.bet.match;

import com.matchbet.bet.bet.BetEntity;
import com.matchbet.bet.bet.BetService;
import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.match.dto.MatchRequestDto;
import com.matchbet.bet.match.dto.MatchResponseDto;
import com.matchbet.bet.match.dto.MatchUpdateScoreRequestDto;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchService {

    private MatchRepository matchRepo;

//    @Autowired
//    private BetService betService;
    private MatchMapper matchMapper;

    public List<MatchResponseDto> getMatchesByTournamentId(Long tournamentId) {
        return matchRepo.findByTournament_id(tournamentId).stream()
                .map(matchMapper::mapToMatchResponseDto)
                .toList();
    }

    public MatchResponseDto getMatch(Long matchId) throws NotFoundException {
        MatchEntity matchEntity = getMatchById(matchId);
        return matchMapper.mapToMatchResponseDto(matchEntity);
    }

    public MatchResponseDto createMatch(MatchRequestDto matchDto) {
        MatchEntity matchEntity = matchRepo.save(matchMapper.mapToEntity(matchDto));
        return matchMapper.mapToMatchResponseDto(matchEntity);
    }

    public MatchResponseDto updateMatch(Long matchId, MatchRequestDto matchDto) throws NotFoundException {
        MatchEntity matchEntity = getMatchById(matchId);
        matchMapper.updateMatch(matchEntity, matchDto);
        return matchMapper.mapToMatchResponseDto(matchEntity);
    }

    public MatchResponseDto updateMatchScore(Long matchId, MatchUpdateScoreRequestDto matchUpdateScoreDto) {
        MatchEntity matchEntity = getMatchById(matchId);
        matchEntity.getBets().forEach(bet -> updatePointsForBet(bet, matchUpdateScoreDto));
        matchMapper.upradeMatchScore(matchEntity, matchUpdateScoreDto);

        return matchMapper.mapToMatchResponseDto(matchEntity);
    }

    public void deleteMatch(Long matchId) {
        matchRepo.deleteById(matchId);
    }

    public MatchEntity getMatchById(Long matchId) throws NotFoundException {
        return matchRepo.findById(matchId)
                .orElseThrow(() -> new NotFoundException("Cannot find match with id %d".formatted(matchId)));
    }

    private void updatePointsForBet(BetEntity bet, MatchUpdateScoreRequestDto matchUpdateScoreDto) {
        int teamAGoals = matchUpdateScoreDto.getTeamAGoalsScored();
        int teamBGoals = matchUpdateScoreDto.getTeamBGoalsScored();
        int teamAGoalsPrediction = bet.getTeamAGoalsScoredPrediction();
        int teamBGoalsPrediction = bet.getTeamBGoalsScoredPrediction();
        if (teamAGoals == teamAGoalsPrediction && teamBGoals == teamBGoalsPrediction) {
            bet.setEarnedPoints(3);
        } else {
            bet.setEarnedPoints(0);
        }
    }

}
