package com.acm.casemanagement.service;

import com.acm.casemanagement.dto.LoginDto;
import com.acm.casemanagement.dto.ResetPasswordDto;
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


    public Optional<User> getUserById(Long id) {
         return userRepository.findById(id);
    }


    public List<User> getAllUsers() {
     return   userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException.UserNotFoundException("User not found with id: " + id));
        user.setActive(false);  // Mark user as inactive
        userRepository.save(user);  // Save the updated user back to the repository
    }
    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        User user = userRepository.findByUsername(resetPasswordDto.getUsername())
                .orElseThrow(() -> new UserException.UserNotFoundException("Invalid username"));

        if (!user.getPassword().equals(resetPasswordDto.getOldPassword())) {
            throw new UserException.InvalidCredentialsException("Invalid current password");
        }

        user.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(user);
        log.info("Password reset successfully for user: {}", resetPasswordDto.getUsername());

    }
    }



