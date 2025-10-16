package com.samadihadis.Banking.service;

import com.samadihadis.Banking.dto.request.AuthenticationResponse;
import com.samadihadis.Banking.dto.request.LoginRequest;
import com.samadihadis.Banking.dto.request.RegisterRequest;
import com.samadihadis.Banking.entity.Username;
import com.samadihadis.Banking.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Key jwtSecretKey;
    private final long jwtExpirationMs = 86400000;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Username user = new Username();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles("ROLE_USER");

        Username savedUser = userRepository.save(user);

        String token = generateToken(convertToUserDetails(savedUser));

        return new AuthenticationResponse(token, "Registration successful");
    }

    public AuthenticationResponse login(LoginRequest request) {
        Username username = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), username.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = generateToken(convertToUserDetails(username));

        return new AuthenticationResponse(token, "Login successful");
    }

    private UserDetails convertToUserDetails(Username user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()))
        );
    }

    private String generateToken(UserDetails user) {
        var roleNames = user.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roleNames)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

}