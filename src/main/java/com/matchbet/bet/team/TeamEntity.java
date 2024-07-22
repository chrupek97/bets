package com.matchbet.bet.team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String nationality;
}
