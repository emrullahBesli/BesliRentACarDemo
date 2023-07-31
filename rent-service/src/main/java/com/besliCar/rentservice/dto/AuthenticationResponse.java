package com.besliCar.rentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
