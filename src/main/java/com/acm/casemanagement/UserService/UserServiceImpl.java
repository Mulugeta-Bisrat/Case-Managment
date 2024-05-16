package com.acm.casemanagement.UserService;

import com.acm.casemanagement.dto.UserDto;
import com.acm.casemanagement.entity.User;
import com.acm.casemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public String login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());

        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            // In a real application, you'd return a JWT or some other token
            return "Login successful!";
        }
        return "Invalid username or password.";
    }


    @Override
    public boolean register(UserDto userDto) {
        return false;
    }
}

