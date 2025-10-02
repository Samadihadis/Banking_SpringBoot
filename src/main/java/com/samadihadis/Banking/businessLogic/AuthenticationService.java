package com.samadihadis.Banking.businessLogic;


import com.samadihadis.Banking.dto.request.AuthenticationResponse;
import com.samadihadis.Banking.dto.request.LoginRequest;
import com.samadihadis.Banking.dto.request.RegisterRequest;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long jwtExpirationMs = 86400000;
    private final List<UserDetails> users;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        if (users.stream().anyMatch(u -> u.getUsername().equals(registerRequest.getUsername()))) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        UserDetails newUser = new User(
                registerRequest.getUsername(),
                hashedPassword,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        users.add(newUser);
        String token = generateToken(newUser);

        return new AuthenticationResponse(token, "Registration successful");
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        UserDetails userDetails = users.stream()
                .filter(u -> u.getUsername().equals(loginRequest.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = generateToken(userDetails);

        return new AuthenticationResponse(token, "Login successful");
    }

    private String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

}
