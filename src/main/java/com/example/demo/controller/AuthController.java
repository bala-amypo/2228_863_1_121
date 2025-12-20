package com.example.demo.controller;

import com.example.demo.service.UserService;   // ✅ REQUIRED
import org.springframework.web.bind.annotation.RequestMapping; // ✅ REQUIRED
import org.springframework.web.bind.annotation.RestController;  // ✅ REQUIRED

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
