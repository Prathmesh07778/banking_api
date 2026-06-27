package com.banking.banking_api.service;

import com.banking.banking_api.dto.request.LoginRequest;
import com.banking.banking_api.dto.request.RegisterRequest;
import com.banking.banking_api.dto.response.ApiResponse;
import com.banking.banking_api.entity.Role;
import com.banking.banking_api.entity.User;
import com.banking.banking_api.repository.UserRepository;
import com.banking.banking_api.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponse register(@Valid RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Email already registered")
                    .build();
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .message("User registered successfully")
                .build();
    }

    public ApiResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Invalid email or password")
                    .build();
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(token)
                .build();
    }
}