package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean validateUser(String email, String password) {
        return email.equals("admin@gmail.com") && password.equals("admin123");
    }
}
