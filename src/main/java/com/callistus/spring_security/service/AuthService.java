package com.callistus.spring_security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.callistus.spring_security.dto.RegisterDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.repository.UserRepository;

@Service
public class AuthService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // private RegisterDto registerDto;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    public User signUp(RegisterDto registerDto) {
        User user = new User().setName(registerDto.getName()).setEmail(registerDto.getEmail())
                .setPassword(this.passwordEncoder.encode(registerDto.getPassword())).setPhone(registerDto.getPhone());
        return userRepository.save(user);

    }

}
