package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        // Dummy validation (replace with DB later)
        if (!userService.validateUser(email, password)) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        Long userId = 1L;
        String role = "ROLE_ADMIN";

        String token = jwtTokenProvider.generateToken(userId, email, role);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", email
        ));
    }
}
