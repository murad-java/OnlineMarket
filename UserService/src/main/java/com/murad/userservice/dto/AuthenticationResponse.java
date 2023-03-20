package com.murad.userservice.dto;

public class AuthenticationResponse {
    private final String jwt;
    private final UserResponse user;

    public AuthenticationResponse(String jwt, UserResponse user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public UserResponse getUser() {
        return user;
    }
}
