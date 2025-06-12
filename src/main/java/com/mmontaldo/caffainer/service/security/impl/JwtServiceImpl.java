package com.mmontaldo.caffainer.service.security.impl;
import com.mmontaldo.caffainer.service.security.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME_MS = 1000 * 3600 * 12; // 12 horas

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        long exp = now + getExpirationTimeMs();
    
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(exp))
            .signWith(key)
            .compact();
    }
    

    private long getExpirationTimeMs() {
        return EXPIRATION_TIME_MS;
    }

    public LocalDateTime getExpirationDateFromToken(String token) {
        Date expiration = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    
        if (expiration == null) {
            throw new IllegalStateException("El token no contiene fecha de expiración.");
        }
    
        return expiration.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }
    

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
