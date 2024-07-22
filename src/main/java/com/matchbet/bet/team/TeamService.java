package com.matchbet.bet.team;

import com.matchbet.bet.exceptions.NotFoundException;
import com.matchbet.bet.team.dto.TeamDto;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamService {
    
    private TeamRepository teamRepo;
    private TeamMapper teamMapper;
    
    public List<TeamDto> getAllTeams(){
        return teamRepo.findAll().stream()
                .map(teamMapper::mapToTeamResponse)
                .toList();
    }
    
    public TeamDto getTeam(Long teamId) throws NotFoundException {
       TeamEntity teamEntity = getTeamById(teamId);
       return teamMapper.mapToTeamResponse(teamEntity);
    }
    
    public TeamDto createTeam(TeamDto teamDto) {
        TeamEntity teamEntity = teamRepo.save(teamMapper.mapToEntity(teamDto));
        return teamMapper.mapToTeamResponse(teamEntity);
    }
    
    public void deleteById(Long id) {
        teamRepo.deleteById(id);
    }
    
    public TeamEntity getTeamById(Long teamId) throws NotFoundException {
        return teamRepo.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Cannot find team with id %d".formatted(teamId)));
    }
}
