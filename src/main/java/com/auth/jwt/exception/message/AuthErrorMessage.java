package com.auth.jwt.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorMessage {
    NO_SUCH_USERNAME_OR_PWD("Incorrect email or password", HttpStatus.UNAUTHORIZED),
    BAD_TOKEN("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);

    private final String msg;
    private final HttpStatus status;

    public int getCode(){
        return status.value();
    }
}
