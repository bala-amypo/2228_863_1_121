package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String jwtSecret;
    private final long jwtExpiration;

    // 🔴 REQUIRED BY TEST SUITE
    public JwtTokenProvider(String jwtSecret, long jwtExpiration) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    // 🔵 REQUIRED BY SPRING
    public JwtTokenProvider() {
        this.jwtSecret = "default-secret-key";
        this.jwtExpiration = 86400000;
    }
}
