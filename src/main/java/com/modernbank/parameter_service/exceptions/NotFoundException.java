package com.modernbank.parameter_service.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private String errorCode;

    @Getter
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}