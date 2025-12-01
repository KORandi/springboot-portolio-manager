package com.example.portfolio.manager.common.util;

import com.example.portfolio.manager.auth.model.User;
import com.example.portfolio.manager.auth.service.AuthService;
import com.example.portfolio.manager.common.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final AuthService authService;

    @Autowired
    UserMapper(AuthService authService) {
        this.authService = authService;
    }

    public UserResponse toResponse(User user) {
        if (authService.getCurrentUser().getId().equals(user.getId())) {
            return new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    null,
                    user.isEnabled()
            );
        }

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled()
        );
    }
}