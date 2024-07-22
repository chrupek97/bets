package com.matchbet.bet.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {

    @NotBlank(message = "Field name cannot be empty")
    private String name;
    private String nationality;
}
