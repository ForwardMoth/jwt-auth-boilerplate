package com.auth.jwt.service.impl;

import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.CustomException;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.repository.UserRepository;
import com.auth.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User create(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new CustomException(UserErrorMessage.EMAIL_EXISTS.getDescription(), HttpStatus.CONFLICT);
        }
        return save(user);
    }

    public User getByEmail(String email){
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomException(
                        UserErrorMessage.USER_NOT_FOUND.getDescription(),
                        HttpStatus.NOT_FOUND)
                );
    }
}
