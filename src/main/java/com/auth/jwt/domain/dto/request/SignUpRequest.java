package com.auth.jwt.domain.dto.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
