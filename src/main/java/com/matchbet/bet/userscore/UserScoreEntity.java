package com.matchbet.bet.userscore;

import com.matchbet.bet.tournament.TournamentEntity;
import com.matchbet.bet.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "user_scores")
public class UserScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private TournamentEntity tournament;
    private int totalPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public UserScoreEntity() {
    }

    public UserScoreEntity(Long id, UserEntity user, TournamentEntity tournament, int totalPoints) {
        this.id = id;
        this.user = user;
        this.tournament = tournament;
        this.totalPoints = totalPoints;
    }
    
    
    
    
}
