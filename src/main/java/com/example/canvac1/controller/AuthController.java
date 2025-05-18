package com.example.canvac1.controller;

import com.example.canvac1.model.User;
import com.example.canvac1.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.canvac1.config.JwtConfig;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                         AuthenticationService authenticationService,
                         JwtConfig jwtConfig,
                         PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }
        
        String token = authorizationHeader.substring(7).trim();
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token cannot be empty");
        }
        
        try {
            String username = jwtConfig.extractUsername(token);
            if (username == null) {
                return ResponseEntity.status(401).body("Invalid token");
            }
            return ResponseEntity.ok(username);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token: " + e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registrationData) {
        String username = registrationData.get("username");
        String password = registrationData.get("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        User registeredUser = authenticationService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            System.out.println("[DEBUG] Login attempt - Username: " + user.getUsername());
            System.out.println("[DEBUG] Starting authentication process...");
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
                )
            );
            
            System.out.println("[DEBUG] Authentication successful");
            System.out.println("[DEBUG] Authenticated user: " + authentication.getName());
            System.out.println("[DEBUG] Setting authentication in SecurityContext");
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            System.out.println("[DEBUG] Generating JWT token for user: " + userDetails.getUsername());
            String jwt = jwtConfig.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e) {
            System.out.println("[DEBUG] Authentication failed: " + e.getMessage()+passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    private record AuthResponse(String token) {}

}
