package com.example.portfolio.manager.common.util;

import com.example.portfolio.manager.auth.model.User;
import com.example.portfolio.manager.common.dto.UserResponse;

public class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static UserResponse toResponse(com.example.portfolio.manager.auth.model.User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled()
        );
    }

    public static UserResponse toResponseWithoutEmail(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                null,
                user.isEnabled()
        );
    }
}