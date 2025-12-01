package com.example.portfolio.manager.auth.controller;

import com.example.portfolio.manager.auth.model.User;
import com.example.portfolio.manager.auth.service.AuthService;
import com.example.portfolio.manager.common.dto.LoginRequest;
import com.example.portfolio.manager.common.dto.RegisterRequest;
import com.example.portfolio.manager.common.dto.UserResponse;
import com.example.portfolio.manager.common.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        try {
            User user = authService.findByUsername(username);
            return ResponseEntity.ok(userMapper.toResponse(user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}