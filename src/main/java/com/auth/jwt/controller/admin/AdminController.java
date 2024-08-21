package com.auth.jwt.controller.admin;

import com.auth.jwt.controller.admin.api.AdminApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController implements AdminApi {

    @GetMapping
    public String get() {
        return "Hello, admin!";
    }
}
