package com.mmontaldo.caffainer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponseDto {
    private String message;
    private String error;
    private Integer status;
    private String path;
    private String dockerHost;
    private String operation;
}
