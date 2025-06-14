package com.mmontaldo.caffainer.exception;

import java.util.Map;

import org.apache.hc.client5.http.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mmontaldo.caffainer.dto.ErrorResponseDto;
import com.mmontaldo.caffainer.exception.security.InvalidTokenException;
import com.mmontaldo.caffainer.exception.security.UnauthorizedAccessException;
import com.mmontaldo.caffainer.exception.security.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(DockerConnectionException.class)
    public ResponseEntity<ErrorResponseDto> handleDockerConnectionException(
            DockerConnectionException ex, WebRequest request) {
        
        logger.error("Docker connection error: {} - Host: {} - Operation: {}", 
                    ex.getMessage(), ex.getDockerHost(), ex.getOperation(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "No se pudo conectar con el servicio Docker",
            "Docker Connection Error",
            HttpStatus.SERVICE_UNAVAILABLE.value(),
            request.getDescription(false).replace("uri=", ""),
            ex.getDockerHost(),
            ex.getOperation()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(HttpHostConnectException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpHostConnectException(
            HttpHostConnectException ex, WebRequest request) {
        
        logger.error("HTTP connection error: {}", ex.getMessage(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Error de conexión con el host Docker",
            "HTTP Connection Error",
            HttpStatus.SERVICE_UNAVAILABLE.value(),
            request.getDescription(false).replace("uri=", ""),
            null,
            null
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(
            RuntimeException ex, WebRequest request) {
        
        logger.error("Runtime error: {}", ex.getMessage(), ex);
        
        // Si es una excepción de Docker envuelta en RuntimeException
        if (ex.getCause() instanceof HttpHostConnectException) {
            return handleHttpHostConnectException((HttpHostConnectException) ex.getCause(), request);
        }
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Error interno del servidor",
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            request.getDescription(false).replace("uri=", ""),
            null,
            null
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex, WebRequest request) {
        
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            "Ha ocurrido un error inesperado",
            "Unexpected Error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            request.getDescription(false).replace("uri=", ""),
            null,
            null
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "error", "Usuario no encontrado",
            "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidToken(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
            "error", "Token no válido",
            "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<?> handleUnauthorized(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
            "error", "Acceso no autorizado",
            "message", ex.getMessage()
        ));
    }

}