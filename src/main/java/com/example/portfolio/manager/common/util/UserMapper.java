package com.example.portfolio.manager.common.util;

import com.example.portfolio.manager.auth.model.User;
import com.example.portfolio.manager.common.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(com.example.portfolio.manager.auth.model.User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled()
        );
    }

    public UserResponse toResponseWithoutEmail(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                null,
                user.isEnabled()
        );
    }
}