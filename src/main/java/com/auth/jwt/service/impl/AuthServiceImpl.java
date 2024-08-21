package com.auth.jwt.service.impl;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.CustomException;
import com.auth.jwt.exception.message.AuthErrorMessage;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.jwt.JwtCore;
import com.auth.jwt.service.AuthService;
import com.auth.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    public JwtAuthResponse signUp(SignUpRequest request){
        String password = request.getPassword();
        if (!Objects.equals(password, request.getConfirmPassword())){
           throw new CustomException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getDescription(), HttpStatus.BAD_REQUEST);
        }

        String email = request.getEmail();
        if (userService.isExisted(email)){
            throw new CustomException(UserErrorMessage.EMAIL_EXISTS.getDescription(), HttpStatus.BAD_REQUEST);
        }

        User user = User.builder().email(email).password(passwordEncoder.encode(password)).build();

        userService.create(user);
        String jwt = jwtCore.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch(BadCredentialsException e){
            throw new CustomException(
                    AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getMsg(),
                    AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getStatus()
            );
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        String jwt = jwtCore.generateToken(userDetails);
        return new JwtAuthResponse(jwt);
    }
}
