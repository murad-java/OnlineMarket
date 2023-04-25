package com.murad.cartservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InfoNotFoundException extends ResponseStatusException {
    public InfoNotFoundException(String message){
        super(HttpStatus.NOT_FOUND,message);
    }
}
