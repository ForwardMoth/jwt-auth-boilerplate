package com.auth.jwt.controller;

import com.auth.jwt.controller.api.HelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController implements HelloApi {

    @GetMapping
    public String get(){
        return "Hello, world";
    }
}
