package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.service.AuthenticationService;
import com.samadihadis.Banking.dto.request.AuthenticationResponse;
import com.samadihadis.Banking.dto.request.LoginRequest;
import com.samadihadis.Banking.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentications")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Validated RegisterRequest registerRequest) {
        try {
            var registerResponse = authenticationService.register(registerRequest);
            return ResponseEntity.ok(registerResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        try {
            var registerResponse = authenticationService.login(loginRequest);
            return ResponseEntity.ok(registerResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

