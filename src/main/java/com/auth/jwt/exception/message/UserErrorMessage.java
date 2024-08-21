package com.auth.jwt.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorMessage {
    EMAIL_EXISTS("User with such email exists"),
    PASSWORD_IS_NOT_SAME("Passwords are not the same");

    private final String description;
}
