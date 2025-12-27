package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

    private final Key key;
    private final long validityInMs;

    public JwtTokenProvider(String secret, long validityInMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMs = validityInMs;
    }

    public String generateToken(Long userId, String email, String role) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ REQUIRED BY TESTS
    public String getEmailFromToken(String token) {
        return parseClaims(token).get("email", String.class);
    }

    // ✅ REQUIRED BY TESTS
    public String getRoleFromToken(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // ✅ REQUIRED BY TESTS
    public Long getUserIdFromToken(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
