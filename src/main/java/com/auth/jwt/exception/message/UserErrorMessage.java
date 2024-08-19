package com.auth.jwt.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorMessage {
    EMAIL_EXISTS("User with such email exists"),
    USER_NOT_FOUND("User with such email is not found");

    private final String description;
}
