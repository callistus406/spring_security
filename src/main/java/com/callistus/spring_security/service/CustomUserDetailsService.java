package com.callistus.spring_security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.callistus.spring_security.model.User;
import com.callistus.spring_security.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
    }


    public List<User> findAllUsers() {
        
        return userRepository.find();
    }

}
