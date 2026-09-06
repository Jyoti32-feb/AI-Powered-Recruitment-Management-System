package com.jyoti.ai_recruitment_management.controller;

import com.jyoti.ai_recruitment_management.dto.RegisterRequest;
import com.jyoti.ai_recruitment_management.entity.User;
import com.jyoti.ai_recruitment_management.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return ResponseEntity.ok(user);
    }

}