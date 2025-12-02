package com.example.portfolio.manager.auth.controller;

import com.example.portfolio.manager.auth.model.User;
import com.example.portfolio.manager.auth.service.AuthService;
import com.example.portfolio.manager.common.dto.AuthResponse;
import com.example.portfolio.manager.common.dto.LoginRequest;
import com.example.portfolio.manager.common.dto.RegisterRequest;
import com.example.portfolio.manager.common.dto.UserResponse;
import com.example.portfolio.manager.common.util.UserMapper;
import com.example.portfolio.manager.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthService authService, UserMapper userMapper,
                          JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        AuthResponse response = new AuthResponse(token, userMapper.toResponse(user));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = authService.login(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        AuthResponse response = new AuthResponse(token, userMapper.toResponse(user));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        User user = authService.findByUsername(username);
        User currentUser = authService.getCurrentUser();
        if (currentUser != null && currentUser.getId().equals(user.getId())) {
            return ResponseEntity.ok(userMapper.toResponse(user));
        }
        return ResponseEntity.ok(userMapper.toResponseWithoutEmail(user));
    }
}