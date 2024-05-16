package com.acm.casemanagement.controller;

import com.acm.casemanagement.UserService.UserService;
import com.acm.casemanagement.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        if (userService.register(userDto)) {
            return ResponseEntity.ok("Registration successful!");
        } else {
            return ResponseEntity.badRequest().body("Registration failed. User might already exist.");
        }
    }

    //response has to be userdto 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String response = userService.login(userDto);
        if ("Login successful!".equals(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}

