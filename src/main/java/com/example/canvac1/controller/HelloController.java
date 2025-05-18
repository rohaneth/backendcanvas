package com.example.canvac1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.Collections;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Collections.singletonMap("status", "UP");
    }
}

