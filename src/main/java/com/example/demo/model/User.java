package com.example.demo.model;

import jakarta.presistence.*;

@Entity  
@Table(name="users")

public class User{
    @Id
    @GeneratedValue

    private long id;

    @column(unique = true)

    private String email;
    private String name;
    private String password;
    private String role ="Staff";
}