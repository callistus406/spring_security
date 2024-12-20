package com.callistus.spring_security.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_table")
public class User implements UserDetails  {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)

    private String email;
    @Column(nullable = false)

    private String password;
    @Column(nullable = false)

    private String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER_ROLE"));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {

        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;

    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;

    }

    public User setPassword(String password) {
        this.password = password;
        return this;

    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
