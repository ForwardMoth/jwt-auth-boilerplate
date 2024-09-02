package com.auth.jwt.service.impl;

import com.auth.jwt.domain.dto.request.JwtRefreshTokenRequest;
import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.message.AuthErrorMessage;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.exception.type.BadRequestException;
import com.auth.jwt.exception.type.UnauthorizedException;
import com.auth.jwt.jwt.JwtCore;
import com.auth.jwt.service.AuthService;
import com.auth.jwt.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(SignUpRequest request) {
        String password = request.getPassword();
        if (!Objects.equals(password, request.getConfirmPassword())) {
            throw new BadRequestException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getMessage());
        }

        String email = request.getEmail();
        if (userService.isExisted(email)) {
            throw new BadRequestException(UserErrorMessage.EMAIL_EXISTS.getMessage());
        }

        User user = User.builder().email(email).password(passwordEncoder.encode(password)).build();

        userService.create(user);
        return new JwtAuthResponse(
                jwtCore.generateToken(user, true),
                jwtCore.generateToken(user, false)
        );
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getMessage());
        }

        User user = userService.findByEmail(request.getEmail());

        return new JwtAuthResponse(
                jwtCore.generateToken(user, true),
                jwtCore.generateToken(user, false)
        );
    }

    @Override
    public JwtAuthResponse refresh(JwtRefreshTokenRequest request) {
        try {
            String refreshToken = request.getRefreshToken();
            String email = jwtCore.extractEmail(refreshToken, false);
            User user = userService.findByEmail(email);
            return new JwtAuthResponse(
                    jwtCore.generateToken(user, true),
                    refreshToken
            );
        } catch (JwtException | IllegalArgumentException e) {
            throw new BadRequestException(AuthErrorMessage.INVALID_OR_EXPIRED_TOKEN.getMessage());
        }
    }
}
