package com.modernbank.parameter_service.exceptions;

import lombok.Getter;

public class SecuriyException extends RuntimeException {

    @Getter
    private String message;

    public SecuriyException(String message) {
        super(message);
        this.message = message;
    }
}