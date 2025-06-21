package com.someorg.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -4499488407025960939L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
