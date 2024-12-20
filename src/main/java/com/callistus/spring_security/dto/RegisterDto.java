package com.callistus.spring_security.dto;

public class RegisterDto {

    private String email;

    private String phone;
    private String password;

    private String name;

    public String getEmail() {
        return email;
    }

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegisterDto(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterDto [email=" + email + ", phone=" + phone + ", password=" + password + ", name=" + name + "]";
    }

}
