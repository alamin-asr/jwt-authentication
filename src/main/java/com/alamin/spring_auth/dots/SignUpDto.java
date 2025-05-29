package com.alamin.spring_auth.dots;

import com.alamin.spring_auth.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role
    ) {
}
