package com.example.demo.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class JwtTokenProvider {
    private final String secret;
    private final int expiration;
    public JwtTokenProvider(
            @Value("${jwt.secret:defaultSecret}") String secret,
            @Value("${jwt.expiration:3600000}") int expiration
    ) {
        this.secret = secret;
        this.expiration = expiration;
    }
    public String generateToken(Long userId, String email, String role) {
        return userId + "|" + email + "|" + role;
    }
    public boolean validateToken(String token) {
        return token != null && token.contains("|");
    }
    public Long getUserIdFromToken(String token) {
        if (token == null) return null;
        return Long.parseLong(token.split("\\|")[0]);
    }

    public String getEmailFromToken(String token) {
        if (token == null) return null;
        return token.split("\\|")[1];
    }

    public String getRoleFromToken(String token) {
        if (token == null) return null;
        return token.split("\\|")[2];
    }
}
