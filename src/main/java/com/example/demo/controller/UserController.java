package com.example.demo.controller;

import com.example.demo.model.Faculty;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Faculty user) {
        String result = userService.register(user);
        if ("Registration successful!".equals(result)) {
            return ResponseEntity.ok(new ResponseMessage("Registration successful!"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage(result));
        }
    }

    // ResponseMessage class
    public static class ResponseMessage {
        private String message;
        public ResponseMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }


    // Updated to accept login credentials in the request body
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userId, @RequestParam String password) {

        String result = userService.login(userId, password);
        if ("Login successful!".equals(result)) {

            return ResponseEntity.ok(new ResponseMessage("Login successful!"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage(result));
        }
    }

    // ResponseMessage class to wrap the message in a JSON object
    public static class ResponseMessage1 {
        private String message;

        public ResponseMessage1(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
