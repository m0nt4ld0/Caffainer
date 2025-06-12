package com.mmontaldo.caffainer.controller.security;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmontaldo.caffainer.dto.security.AuthRequestDto;
import com.mmontaldo.caffainer.dto.security.AuthResponseDto;
import com.mmontaldo.caffainer.service.security.impl.JwtServiceImpl;
import com.mmontaldo.caffainer.service.user.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public record AuthController(JwtServiceImpl jwtService, UserServiceImpl userService)  {

    @PostMapping("/authenticate")
    public ResponseEntity<?> getToken(@RequestBody AuthRequestDto request) 
        throws UsernameNotFoundException 
    {
        String username = request.username();
        if(userService.getUserByUsername(username) != null)
        {
            String token = jwtService.generateToken(username);
            LocalDateTime expiration = jwtService.getExpirationDateFromToken(token);
        
            return ResponseEntity.ok(new AuthResponseDto (
                username, 
                token,
                expiration
            ));
        } else {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("El usuario " + username + " no existe");
        }
    }

}
