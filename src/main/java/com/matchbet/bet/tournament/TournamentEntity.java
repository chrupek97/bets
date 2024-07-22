package com.matchbet.bet.tournament;

import com.matchbet.bet.bet.BetEntity;
import com.matchbet.bet.team.TeamEntity;
import com.matchbet.bet.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "tournaments")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity creator;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();

    @ManyToMany
    private List<TeamEntity> teams = new ArrayList<>();

    @OneToMany(mappedBy = "tournament")
    private List<BetEntity> bets = new ArrayList<>();

    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<BetEntity> getBets() {
        return bets;
    }

    public void setBets(List<BetEntity> bets) {
        this.bets = bets;
    }

    public TournamentEntity() {
    }

    public TournamentEntity(Long id, UserEntity creator, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

}
