package com.example.pohovor.auth.controller;

import com.example.pohovor.auth.model.User;
import com.example.pohovor.auth.service.AuthService;
import com.example.pohovor.common.dto.LoginRequest;
import com.example.pohovor.common.dto.RegisterRequest;
import com.example.pohovor.common.dto.UserResponse;
import com.example.pohovor.common.util.UserMapper;
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
        try {
            User user = authService.register(
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail()
            );
            return ResponseEntity.ok(userMapper.toResponse(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            User user = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(userMapper.toResponse(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
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