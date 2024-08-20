package com.auth.jwt.service;

import com.auth.jwt.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);

    Optional<User> getByEmail(String email);
}
