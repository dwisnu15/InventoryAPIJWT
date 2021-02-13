package com.enigma.api.inventory.validations;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public ValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
