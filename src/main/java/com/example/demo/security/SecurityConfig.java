package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {

    // 🔹 Used when application runs normally (web mode)
    @Bean
    @Profile("!test")
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 🔹 Used ONLY for test01_simulated_application_start
    @Bean
    @Profile("test")
    public AuthenticationManager testAuthenticationManager() {
        return authentication -> authentication;
    }
}
