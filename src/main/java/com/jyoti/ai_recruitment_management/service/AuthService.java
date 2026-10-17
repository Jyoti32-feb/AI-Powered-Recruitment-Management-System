package com.jyoti.ai_recruitment_management.service;

import com.jyoti.ai_recruitment_management.dto.LoginRequest;
import com.jyoti.ai_recruitment_management.dto.RegisterRequest;
import com.jyoti.ai_recruitment_management.dto.UserResponse;
import com.jyoti.ai_recruitment_management.entity.Role;
import com.jyoti.ai_recruitment_management.entity.User;
import com.jyoti.ai_recruitment_management.exception.EmailAlreadyExitsException;
import com.jyoti.ai_recruitment_management.exception.InvalidCredentialsException;
import com.jyoti.ai_recruitment_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jyoti.ai_recruitment_management.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExitsException("Email already registered");
        }


        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());


        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.CANDIDATE);


        User saveduser= userRepository.save(user);

        return new UserResponse(saveduser.getId(), saveduser.getName(), saveduser.getEmail(), saveduser.getRole());
    }
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        String token = jwtService.generateToken(user);

        return new LoginResponse(userResponse, token);
    }
}