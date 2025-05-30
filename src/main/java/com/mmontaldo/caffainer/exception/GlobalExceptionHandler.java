package com.mmontaldo.caffainer.exception;

import org.apache.hc.client5.http.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mmontaldo.caffainer.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(DockerConnectionException.class)
    public ResponseEntity<ErrorResponseDto> handleDockerConnectionException(
            DockerConnectionException ex, WebRequest request) {
        
        logger.error("Docker connection error: {} - Host: {} - Operation: {}", 
                    ex.getMessage(), ex.getDockerHost(), ex.getOperation(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage("No se pudo conectar con el servicio Docker");
        errorResponse.setError("Docker Connection Error");
        errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setDockerHost(ex.getDockerHost());
        errorResponse.setOperation(ex.getOperation());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(HttpHostConnectException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpHostConnectException(
            HttpHostConnectException ex, WebRequest request) {
        
        logger.error("HTTP connection error: {}", ex.getMessage(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage("Error de conexión con el host Docker");
        errorResponse.setError("HTTP Connection Error");
        errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        
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
        
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage("Error interno del servidor");
        errorResponse.setError("Internal Server Error");
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex, WebRequest request) {
        
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage("Ha ocurrido un error inesperado");
        errorResponse.setError("Unexpected Error");
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}