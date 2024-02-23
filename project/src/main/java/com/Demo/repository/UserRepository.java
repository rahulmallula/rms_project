package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Demo.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
