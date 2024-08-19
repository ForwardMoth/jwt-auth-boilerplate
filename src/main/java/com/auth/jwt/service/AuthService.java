package com.auth.jwt.service;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
