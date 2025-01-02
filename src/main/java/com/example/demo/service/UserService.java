package com.example.demo.service;

import com.example.demo.model.Faculty;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Register method without password encoding
    public String register(Faculty user) {
        if (userRepository.findByUserId(user.getUserId()) != null) {
            return "User ID already exists!";
        }
        // Store the password as plain text (not recommended for production)
        userRepository.save(user);
        return "Registration successful!";
    }

    // Login method without password encoding
    public String login(String userId, String password) {
        Faculty user = userRepository.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid credentials.";
    }
}
