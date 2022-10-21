package com.example.vcnb;

import com.example.vcnb.dto.UserRegistrationDto;
import com.example.vcnb.model.Role;
import com.example.vcnb.model.User;
import com.example.vcnb.repository.IRoleRepository;
import com.example.vcnb.repository.IUserRepository;
import com.example.vcnb.service.IUserService;
import groovy.lang.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;



    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createUserIfNotFound(new UserRegistrationDto("admin","admin","admin@admin.com","123456"));
    }
    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
    @Transactional
    User createUserIfNotFound(UserRegistrationDto registrationDto) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            user = userService.save(registrationDto);
        }
        return user;
    }
}
