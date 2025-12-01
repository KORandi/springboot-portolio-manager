package com.example.portfolio.manager.auth.service;

import com.example.portfolio.manager.auth.model.User;

public interface AuthService {

    User register(String username, String password, String email);

    User login(String username, String password);

    User findByUsername(String username);

    User getCurrentUser();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}