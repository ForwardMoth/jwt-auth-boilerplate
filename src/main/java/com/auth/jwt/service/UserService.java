package com.auth.jwt.service;

import com.auth.jwt.domain.model.User;

public interface UserService {
    User save(User user);

    User create(User user);

    User getByEmail(String email);
}
