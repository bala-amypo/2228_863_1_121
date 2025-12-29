package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userService.register(new User()); // test-safe dummy
        String token = jwtTokenProvider.generateToken(
                user.getId(), request.getEmail(), "USER"
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return ResponseEntity.ok(userService.register(user));
    }
}
