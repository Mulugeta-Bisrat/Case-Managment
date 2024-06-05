package com.acm.casemanagement.service;

import com.acm.casemanagement.dto.LoginDto;
import com.acm.casemanagement.dto.UserDto;
import com.acm.casemanagement.entity.User;
import com.acm.casemanagement.exception.UserException;
import com.acm.casemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            log.error("Username already taken: {}", userDto.getUsername());
            throw new UserException.UserAlreadyExistsException("Username already taken");
        }
        User user = User.builder()
                .email(userDto.getEmail())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .isActive(userDto.isActive())
                .build();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        log.info("User registered successfully: {}", userDto.getUsername());
        return userRepository.save(user);
    }

    public User loginUser(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new UserException.InvalidCredentialsException("Invalid username or password"));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            throw new UserException.InvalidCredentialsException("Invalid username or password");
        }
        return user;
    }


    public User getUserById(Long id) {
        return (userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new UserException.UserNotFoundException("User not found or is inactive with id: " + id)));


    }





}


