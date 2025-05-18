package com.example.canvac1.controller;

import com.example.canvac1.config.JwtConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JwtConfig jwtConfig;

    public UserController(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

   
}
