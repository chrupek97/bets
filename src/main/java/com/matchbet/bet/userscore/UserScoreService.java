package com.matchbet.bet.userscore;

import com.matchbet.bet.bet.BetEntity;
import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.match.dto.MatchUpdateScoreRequestDto;
import com.matchbet.bet.userscore.dto.UserScoreRequestDto;
import com.matchbet.bet.userscore.dto.UserScoreResponseDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserScoreService {

    @Autowired
    private UserScoreRepository userScoreRepo;

    @Autowired
    private UserScoreMapper userScoreMapper;

    public List<UserScoreResponseDto> getUserScoresByTournamentId(Long tournamentId) {
        return userScoreRepo.findByTournament_id(tournamentId).stream()
                .map(userScoreMapper::mapToUserScoreResponseDto)
                .toList();
    }

    public UserScoreResponseDto createUserScore(UserScoreRequestDto userScoreDto) {
        UserScoreEntity userScoreEntity = userScoreRepo.save(userScoreMapper.mapToUserScoreEntity(userScoreDto));
        return userScoreMapper.mapToUserScoreResponseDto(userScoreEntity);
    }

    public UserScoreResponseDto getUserScore(Long userScoreId) throws NotFoundException {
        UserScoreEntity userScoreEnity = getUserScoreById(userScoreId);
        return userScoreMapper.mapToUserScoreResponseDto(userScoreEnity);
    }

    public UserScoreResponseDto updateUserScore(Long userScoreId, UserScoreRequestDto userScoreRequestDto) throws NotFoundException {
        UserScoreEntity userScoreEntity = getUserScoreById(userScoreId);
        userScoreMapper.updateUserScoreEntity(userScoreEntity, userScoreRequestDto);
        return userScoreMapper.mapToUserScoreResponseDto(userScoreEntity);
    }

    public void deleteById(Long userScoreId) {
        userScoreRepo.deleteById(userScoreId);
    }

    public UserScoreEntity getUserScoreById(Long userScoreId) throws NotFoundException {
        return userScoreRepo.findById(userScoreId)
                .orElseThrow(() -> new NotFoundException("Cannot find user score with id %d".formatted(userScoreId)));
    }
}
