package com.mmontaldo.caffainer.service.security;

public interface JwtService {

    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
