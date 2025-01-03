package com.callistus.spring_security.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.callistus.spring_security.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> find();
}
