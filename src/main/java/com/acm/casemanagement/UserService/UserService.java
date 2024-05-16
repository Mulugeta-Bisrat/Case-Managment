package com.acm.casemanagement.UserService;

import com.acm.casemanagement.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    String login(UserDto userDto);

//    UserDetails loadUserByUsername(String s);

    boolean register(UserDto userDto);
}
