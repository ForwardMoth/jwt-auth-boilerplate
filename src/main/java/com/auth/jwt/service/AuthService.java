package com.auth.jwt.service;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    ResponseEntity<?> signIn(SignInRequest request);
}
