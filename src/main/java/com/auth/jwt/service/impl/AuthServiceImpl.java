package com.auth.jwt.service.impl;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import com.auth.jwt.domain.enums.RoleEnum;
import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.CustomException;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.jwt.JwtCore;
import com.auth.jwt.service.AuthService;
import com.auth.jwt.service.RoleService;
import com.auth.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserService userService;
    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(SignUpRequest request){
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())){
            throw new CustomException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getDescription(), HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.getByName(RoleEnum.USER))
                .build();

        userService.create(user);
        String jwt = jwtCore.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        }catch (AuthenticationException e){
            throw new CustomException(UserErrorMessage.INVALID_AUTH.getDescription(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        String jwt = jwtCore.generateToken(user);
        return new JwtAuthResponse(jwt);
    }
}
