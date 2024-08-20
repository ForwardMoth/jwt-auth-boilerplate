package com.auth.jwt.service.impl;

import com.auth.jwt.domain.model.User;
import com.auth.jwt.exception.CustomException;
import com.auth.jwt.exception.message.UserErrorMessage;
import com.auth.jwt.repository.UserRepository;
import com.auth.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public User getCurrentUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }
}
