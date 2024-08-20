package com.auth.jwt.exception.message;

import lombok.Builder;

@Builder
public record ErrorMessage(String code, String message) {
}
