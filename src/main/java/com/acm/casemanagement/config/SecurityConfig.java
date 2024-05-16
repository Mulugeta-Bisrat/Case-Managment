package com.acm.casemanagement.config;

import com.acm.casemanagement.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig<SecurityFilterChain> {
    @Autowired
    private UserServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public org.springframework.security.web.DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/register", "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().disable();
//        return http.build();
//    }

//    @Bean
//    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService::loadUserByUsername)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
}

