package com.auth.jwt.service;

import com.auth.jwt.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    User save(User user);

    User create(User user);

    User getByEmail(String email);

    UserDetailsService userDetailsService();

    User getCurrentUser();

    Optional<User> findOne(String email);
}
