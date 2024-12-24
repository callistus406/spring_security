package com.callistus.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.callistus.spring_security.dto.UserDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.service.AuthService;

@RestController
public class UserController {

    @Autowired
    AuthService authService;

      @GetMapping("/me")
    public UserDto  login(@RequestBody String email) {
        
        User user = authService.me(email);
        System.out.println(user.getEmail());
        UserDto userDto = new UserDto(user.getEmail(), user.getName());
        return userDto;
    }
}
