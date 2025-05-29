package com.alamin.spring_auth.dots;

import com.alamin.spring_auth.enums.UserRole;
public record SignInDto(
        String login,
        String password
) {
}