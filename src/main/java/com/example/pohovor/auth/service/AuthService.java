package com.example.pohovor.auth.service;

import com.example.pohovor.auth.model.User;

public interface AuthService {

    User register(String username, String password, String email);

    User login(String username, String password);

    User findByUsername(String username);

    User getCurrentUser();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}