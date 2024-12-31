package com.callistus.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callistus.spring_security.dto.LoginDto;
import com.callistus.spring_security.dto.LoginResponse;
import com.callistus.spring_security.dto.RegisterDto;
import com.callistus.spring_security.model.User;
import com.callistus.spring_security.service.AuthService;
import com.callistus.spring_security.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    JwtService jwtService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDto entity) {

        var res = this.authService.signUp(entity);
        return res;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto entity) {

        User user = this.authService.signIn(entity);
        String token = jwtService.generateJwt(user);
        LoginResponse response = new LoginResponse().setToken(token).setExpiresIn(jwtService.getJwtExpiration());
        return ResponseEntity.ok(response);
    }

}
