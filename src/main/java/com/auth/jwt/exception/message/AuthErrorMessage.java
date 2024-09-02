package com.auth.jwt.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorMessage {
    NO_SUCH_USERNAME_OR_PWD("Incorrect email or password"),
    INVALID_OR_EXPIRED_TOKEN("Invalid or expired token");

    private final String message;
}
