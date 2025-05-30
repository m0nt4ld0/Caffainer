package com.mmontaldo.caffainer.exception;

public class DockerConnectionException extends RuntimeException {
    
    private final String dockerHost;
    private final String operation;
    
    public DockerConnectionException(String message, String dockerHost, String operation) {
        super(message);
        this.dockerHost = dockerHost;
        this.operation = operation;
    }
    
    public DockerConnectionException(String message, Throwable cause, String dockerHost, String operation) {
        super(message, cause);
        this.dockerHost = dockerHost;
        this.operation = operation;
    }
    
    public String getDockerHost() {
        return dockerHost;
    }
    
    public String getOperation() {
        return operation;
    }
}