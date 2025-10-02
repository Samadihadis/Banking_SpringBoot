package com.samadihadis.Banking.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String message;

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
