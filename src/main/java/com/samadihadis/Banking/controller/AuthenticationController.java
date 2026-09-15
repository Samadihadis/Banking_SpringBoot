package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.service.AuthenticationService;
import com.samadihadis.Banking.dto.request.AuthenticationResponse;
import com.samadihadis.Banking.dto.request.LoginRequest;
import com.samadihadis.Banking.dto.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
            AuthenticationResponse registerResponse = authenticationService.register(registerRequest);
            return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
            AuthenticationResponse loginResponse = authenticationService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
    }
}

