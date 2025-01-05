package com.callistus.spring_security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.callistus.spring_security.dto.UserDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.service.AuthService;
import com.callistus.spring_security.service.CustomUserDetailsService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

      @GetMapping("/me")
      public UserDto login(@RequestBody String email) {

        User user = authService.me(email);
     
        UserDto userDto = new UserDto(user.getEmail(), user.getName());
        return userDto;
      }
    
      @GetMapping("/users")
      public List<User> getMethodName() {
        return this.customUserDetailsService.findAllUsers();
      }

      @GetMapping("/users/:id")
      public Optional<User> getMethodName(@RequestParam Integer id) {
          return this.customUserDetailsService.findUserById(id);
      }
      
      
}
