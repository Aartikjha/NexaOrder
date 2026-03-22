package com.project.user_service.service;

import com.project.user_service.model.User;
import com.project.user_service.repository.UserRepository;
import com.project.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public String register(User user) {

        // Check if email already exists
        Optional<User> existing =
                userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return "Email already registered!";
        }

        // Encrypt password before saving
        user.setPassword(encoder.encode(user.getPassword()));

        // Set default role if not provided
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(User user) {

        // Find user by email
        Optional<User> existing =
                userRepository.findByEmail(user.getEmail());

        if (existing.isEmpty()) {
            return "User not found!";
        }

        User savedUser = existing.get();

        // Check if password matches
        boolean matches = encoder.matches(
                user.getPassword(), savedUser.getPassword());

        if (!matches) {
            return "Invalid password!";
        }

        // Generate and return JWT token
        return jwtUtil.generateToken(
                savedUser.getEmail(), savedUser.getRole());
    }
}