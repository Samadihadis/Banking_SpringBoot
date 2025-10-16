package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Username, Long> {

    Optional<Username> findByUsername(String username);
    boolean existsByUsername(String username);
}
