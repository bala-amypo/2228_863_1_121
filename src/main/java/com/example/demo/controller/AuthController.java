package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") 
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

   @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody AuthRequest request) {
 
    String encodedPassword = passwordEncoder.encode(request.getPassword());
 
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(encodedPassword);
    user.setRole("USER");
 
    User savedUser = userService.register(user);
 
    String token = jwtTokenProvider.generateToken(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getRole()
    );
 
    return ResponseEntity.ok(
            new AuthResponse(
                    token,
                    savedUser.getId(),
                    savedUser.getEmail(),
                    savedUser.getRole()
            )
    );
}


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(AuthRequest req){ 
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid auth"));
        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
