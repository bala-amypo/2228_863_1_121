package com.example.demo.dto;

@Data
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}