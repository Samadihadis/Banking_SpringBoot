package com.samadihadis.Banking.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Key jwtSecretKey;

    public JwtAuthenticationFilter(Key jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if (username == null || username.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }

            Object rolesObj = claims.get("roles");
            List<SimpleGrantedAuthority> authorities = List.of();

            if (rolesObj instanceof List<?> list) {
                if (!list.isEmpty() && list.get(0) instanceof String) {
                    authorities = list.stream()
                            .map(r -> new SimpleGrantedAuthority((String) r))
                            .collect(Collectors.toList());
                } else {
                    authorities = list.stream()
                            .map(it -> {
                                if (it instanceof Map<?, ?> m) {
                                    Object a = m.get("authority");
                                    return (a == null) ? null : new SimpleGrantedAuthority(a.toString());
                                }
                                return null;
                            })
                            .filter(x -> x != null && !x.getAuthority().isBlank())
                            .collect(Collectors.toList());
                }
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            System.out.println("JWT ERROR: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
