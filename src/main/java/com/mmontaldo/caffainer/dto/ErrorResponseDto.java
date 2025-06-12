package com.mmontaldo.caffainer.dto;

public record ErrorResponseDto(
    String message,
    String error,
    Integer status,
    String path,
    String dockerHost,
    String operation
) {}