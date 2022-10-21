package com.example.vcnb.service;

import com.example.vcnb.dto.UserRegistrationDto;
import com.example.vcnb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);

}
