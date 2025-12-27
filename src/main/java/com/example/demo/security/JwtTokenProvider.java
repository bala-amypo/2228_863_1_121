package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // 🔐 HARD-CODED SECRET (must be at least 32 characters for HS256)
    private static final String SECRET_KEY =
            "MY_SUPER_SECRET_JWT_KEY_9f3A7xQ2Lk8M0R4VZpT6H1WJ";

    // ⏱ Token validity: 1 hour
    private static final long VALIDITY_IN_MS = 60 * 60 * 1000;

    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(Long userId, String email, String role) {

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + VALIDITY_IN_MS);

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

    // ✅ REQUIRED BY YOUR TESTS
    public String getEmailFromToken(String token) {
        return parseClaims(token).get("email", String.class);
    }

    // ✅ REQUIRED BY YOUR TESTS
    public String getRoleFromToken(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // ✅ REQUIRED BY YOUR TESTS
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
