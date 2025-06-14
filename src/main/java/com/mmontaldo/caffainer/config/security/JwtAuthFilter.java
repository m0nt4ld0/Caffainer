package com.mmontaldo.caffainer.config.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmontaldo.caffainer.exception.security.InvalidTokenException;
import com.mmontaldo.caffainer.exception.security.UserNotFoundException;
import com.mmontaldo.caffainer.service.security.impl.JwtServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;

    public JwtAuthFilter(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                if (!jwtService.validateToken(token)) {
                    throw new InvalidTokenException("El token proporcionado no es válido o ha expirado.");
                }

                String username = jwtService.getUsernameFromToken(token);
                if (username == null) {
                    throw new UserNotFoundException("El usuario proporcionado no coincide con el usuario del token o no está registrado.");
                }

                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, null);

                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);

        } catch (InvalidTokenException | UserNotFoundException ex) {
            handleAuthException(response, ex.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Exception ex) {
            handleAuthException(response, "Error interno durante la autenticación", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleAuthException(HttpServletResponse response, String message, int statusCode)
            throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");

        var json = new ObjectMapper().writeValueAsString(
            Map.of("error", "Unauthorized", "message", message)
        );

        response.getWriter().write(json);
    }

}
