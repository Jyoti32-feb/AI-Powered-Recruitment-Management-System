package com.jyoti.ai_recruitment_management.service;

import com.jyoti.ai_recruitment_management.dto.RegisterRequest;
import com.jyoti.ai_recruitment_management.dto.UserResponse;
import com.jyoti.ai_recruitment_management.entity.User;
import com.jyoti.ai_recruitment_management.exception.EmailAlreadyExitsException;
import com.jyoti.ai_recruitment_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExitsException("Email already registered");
        }


        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());


        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());


        User saveduser= userRepository.save(user);

        return new UserResponse(saveduser.getId(), saveduser.getName(), saveduser.getEmail(), saveduser.getRole());
    }
}