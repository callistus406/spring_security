package com.callistus.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.callistus.spring_security.dto.LoginDto;
import com.callistus.spring_security.dto.RegisterDto;
import com.callistus.spring_security.dto.UserDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.repository.UserRepository;

@Service
public class AuthService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    public User signIn(LoginDto loginDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        return userRepository.findByEmail(loginDto.getEmail()).orElseThrow();

    }

    public User me(String email) {

        var user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                System.out.println("==================");
        return user;

    }

}
