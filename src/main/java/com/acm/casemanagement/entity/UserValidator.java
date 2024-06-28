package com.acm.casemanagement.entity;

import com.acm.casemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        //Check uniqueness of username
        if (userRepository.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "Username already exists", "Username already exists");
        }
        // Check uniqueness of email
        if (userRepository.existsByEmail(user.getEmail())) {
            errors.rejectValue("email", "Email already exists", "Email already exists");
        }
        // Validate password (already done via annotations, but adding custom logic if needed)
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Password too short", "Password must be at least 8 characters long");
        }

        if (!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
            errors.rejectValue("password", "Password not alphanumeric", "Password must contain both letters and numbers");
        }
    }
}

