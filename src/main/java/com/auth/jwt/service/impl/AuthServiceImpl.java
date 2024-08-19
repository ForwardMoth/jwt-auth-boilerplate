package com.auth.jwt.service.impl;

import com.auth.jwt.domain.dto.request.SignInRequest;
import com.auth.jwt.domain.dto.request.SignUpRequest;
import com.auth.jwt.domain.dto.response.JwtAuthResponse;
import com.auth.jwt.domain.enums.RoleEnum;
import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.CustomException;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.service.AuthService;
import com.auth.jwt.service.RoleService;
import com.auth.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserService userService;

    public JwtAuthResponse signUp(SignUpRequest request){
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())){
           throw new CustomException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getDescription(), HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(roleService.getByName(RoleEnum.USER)) // password encoder
                .build();

        userService.create(user);
        String jwt = ""; // add jwt generate method
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request){
        // auth manager
        // load user
        String jwt = ""; // add jwt generate method
        return new JwtAuthResponse(jwt);
    }
}
