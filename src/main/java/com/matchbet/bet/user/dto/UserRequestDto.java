package com.matchbet.bet.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    @NotBlank(message = "Field username cannot be empty")
    private String username;

    @NotBlank(message = "Field password cannot be empty")
    @Size(min = 5, message = "Password should contain at least 5 characters")
    private String password;

    @NotBlank(message = "Field email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
}
