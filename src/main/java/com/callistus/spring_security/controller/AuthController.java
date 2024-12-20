package com.callistus.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callistus.spring_security.dto.RegisterDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDto entity) {

        var res = this.authService.signUp(entity);
        return res;
    }

}
