package com.auth.jwt.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorMessage {
    BAD_TOKEN("Expired or invalid JWT token");

    private final String description;
}
