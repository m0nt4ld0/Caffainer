package com.mmontaldo.caffainer.controller.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmontaldo.caffainer.dto.security.AuthRequestDto;
import com.mmontaldo.caffainer.dto.security.AuthResponseDto;
import com.mmontaldo.caffainer.service.security.impl.JwtServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final JwtServiceImpl jwtService;

    public AuthController(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        String token = jwtService.generateToken(request.username());
        LocalDateTime expiration = jwtService.getExpirationDateFromToken(token);
    
        return ResponseEntity.ok(new AuthResponseDto(
            request.username(), 
            token,
            expiration
        ));
    }
    
}
