package com.auth.jwt.controller;

import com.auth.jwt.controller.api.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController implements UserApi {

    @GetMapping
    public String get() {
        return "Hello, user!";
    }
}

