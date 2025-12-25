package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder){
        this.repo = repo; this.encoder = encoder;
    }

    @Override
    public User register(User u){
        if(u==null || u.getEmail()==null || u.getPassword()==null || u.getName()==null) throw new ApiException("Missing fields");
        if(repo.findByEmail(u.getEmail()).isPresent()) throw new ApiException("Email exists");
        if(u.getRole()==null) u.setRole("STAFF");
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    @Override
    public User findByEmail(String email){
        Optional<User> o = repo.findByEmail(email);
        return o.orElseThrow(() -> new ApiException("User not found"));
    }
}
