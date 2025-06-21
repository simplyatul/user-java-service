package com.someorg.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1884479929231298696L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
