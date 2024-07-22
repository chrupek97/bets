package com.matchbet.bet.auth;

import com.matchbet.bet.auth.dto.JwtResponseDto;
import com.matchbet.bet.auth.dto.AuthRequestDto;
import com.matchbet.bet.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Date;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("/login")
    public JwtResponseDto createToken(@RequestBody AuthRequestDto authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            jwtService.setExpirationInMinutes(60);
            jwtService.setRefreshExpirationDateInMinutes(30);
            String token = jwtService.generateToken(authRequestDTO.getUsername());
            Date expiryDate = jwtService.extractExpiration(token);
            return new JwtResponseDto(token, expiryDate);
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    @GetMapping("/login2")
    public String login2() {
        return "Hellow";
    }

    @GetMapping("/refreshToken")
    public JwtResponseDto refreshToken(HttpServletRequest request) {
        String reqToken = request.getHeader("token");
        if (!jwtService.isTokenExpired(reqToken)) {
            String username = jwtService.extractUsername(reqToken);
            String token = jwtService.refreshToken(username);
            Date expiryDate = jwtService.extractExpiration(token);
            return new JwtResponseDto(token, expiryDate);
        } else {
            throw new RuntimeException("Token is expired.");
        }
    }

}
