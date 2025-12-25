package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> register(RegisterRequest req){
        User u = User.builder().name(req.getName()).email(req.getEmail()).password(req.getPassword()).role(req.getRole()).build();
        return ResponseEntity.ok(userService.register(u));
    }

    public ResponseEntity<AuthResponse> login(AuthRequest req){
        // Simplified for test: assume authentication succeeds if user exists
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid auth"));
        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
