package com.murad.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String message) {
        super(HttpStatus.ALREADY_REPORTED, message);
    }

    public UserAlreadyExistsException(String username, String email) {
        super(HttpStatus.ALREADY_REPORTED, String.format("User with username %s or email %s already exists", username, email));
    }
}