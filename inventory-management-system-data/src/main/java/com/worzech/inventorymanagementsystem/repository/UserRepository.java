package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    User findUserByEmail(String email);
}
