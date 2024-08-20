package com.auth.jwt.controller.superadmin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SuperAdminController {
    @GetMapping
    public String get() {
        return "Hello, super-admin!";
    }
}

