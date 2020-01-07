package com.movies.authentication.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
