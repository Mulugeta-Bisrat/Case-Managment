package com.acm.casemanagement.service;

import com.acm.casemanagement.dto.LoginDto;
import com.acm.casemanagement.dto.UserDto;
import com.acm.casemanagement.entity.User;
import com.acm.casemanagement.exception.UserException;
import com.acm.casemanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User upDateUserById(Long id, UserDto userDto) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstname(userDto.getFirstname());
            existingUser.setLastname(userDto.getLastname());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(userDto.getPassword());
            // Add more fields as needed
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<UserDto> getUserById(long id) {
     Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserException.ResourceNotFoundException("There is User with this id" + id)));
        // Handle the case where the user is not found
        UserDto userDto;
        if (user.isPresent()) {
            userDto = UserDto.builder().firstname(user.get().getFirstname()).lastname(user.get().getLastname())
                    .password(user.get().getPassword()).email(user.get().getEmail()).build();
        } else {
            throw new UserException.ResourceNotFoundException("there user with this id" + id);
        }
        return Optional.ofNullable(userDto);
    }

}
