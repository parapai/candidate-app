package com.vai.test.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/health")
public class HealthCheckController {


	@GetMapping("/check")
    public ResponseEntity<Map<String, String>> healthCheckJson() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
	
