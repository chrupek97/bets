package com.matchbet.bet.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDto {

    @NotBlank(message = "Field username cannot be empty")
    private String username;

    @NotBlank(message = "Field password cannot be empty")
    private String password;
}
