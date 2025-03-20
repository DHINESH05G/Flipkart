package com.example.flipkart.controller;

import com.example.flipkart.model.User;
import com.example.flipkart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user); // Call service method
        return ResponseEntity.ok("User registered successfully!");
}

}
