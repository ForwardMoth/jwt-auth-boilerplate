package com.auth.jwt.exception.message;

import lombok.Builder;

@Builder
public record ErrorMessage(int code, String message) {

}
