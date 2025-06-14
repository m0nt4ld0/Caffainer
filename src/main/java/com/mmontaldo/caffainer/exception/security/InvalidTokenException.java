package com.mmontaldo.caffainer.exception.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String string) {
        super(string);
    }
}
