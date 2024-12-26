package com.callistus.spring_security.dto;

public class LoginResponse {
    private String token;
    private long expiresIn;
  
    @Override
    public String toString() {
        return "LoginResponse [token=" + token + ", expiresIn=" + expiresIn + "]";
    }
    public String getToken() {
        return token;
    }
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
    public long getExpiresIn() {
        return expiresIn;
    }
    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;

    }
    
    

}
