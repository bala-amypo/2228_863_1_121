package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo,
                           BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User register(String name, String email, String password) {
        if (repo.findByEmail(email).isPresent())
            throw new ApiException("email exists");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setRole("STAFF");

        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new ApiException("invalid credentials"));

        if (!encoder.matches(password, user.getPassword()))
            throw new ApiException("invalid credentials");

        return user;
    }
}
