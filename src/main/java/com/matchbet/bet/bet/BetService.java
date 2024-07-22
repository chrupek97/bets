package com.matchbet.bet.bet;

import com.matchbet.bet.bet.dto.BetNewRequestDto;
import com.matchbet.bet.bet.dto.BetRequestDto;
import com.matchbet.bet.bet.dto.BetResponseDto;
import com.matchbet.bet.exceptions.NotFoundException;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BetService {

    private BetRepository betRepo;
    private BetMapper betMapper;

    public List<BetResponseDto> getBetsByUserId(Long userId) {
        return betRepo.findByUser_Id(userId).stream()
                .map(betMapper::mapToBetResponseDto)
                .toList();
    }

    public List<BetEntity> getBetsByMatchId(Long matchId) {
        return betRepo.findByMatch_Id(matchId);
    }

    public List<BetResponseDto> getBetsByTournamentId(Long tournamenId) {
        return betRepo.findByTournament_Id(tournamenId).stream()
                .map(betMapper::mapToBetResponseDto)
                .toList();
    }

    public BetResponseDto getBet(Long betId) throws NotFoundException {
        BetEntity betEntity = getBetById(betId);
        return betMapper.mapToBetResponseDto(betEntity);
    }

    public BetResponseDto createBet(BetNewRequestDto betDto) {
        BetEntity betEntity = betRepo.save(betMapper.mapToEntity(betDto));
        return betMapper.mapToBetResponseDto(betEntity);
    }

    public BetResponseDto updateBet(Long betId, BetRequestDto betDto) throws NotFoundException {
        BetEntity betEntity = getBetById(betId);
        betMapper.updateBet(betEntity, betDto);
        return betMapper.mapToBetResponseDto(betRepo.saveAndFlush(betEntity));
    }

    public void deleteBet(Long betId) {
        betRepo.deleteById(betId);
    }

    private BetEntity getBetById(Long betId) throws NotFoundException {
        return betRepo.findById(betId)
                .orElseThrow(() -> new NotFoundException("Cannot find bet with id %d".formatted(betId)));
    }
}
