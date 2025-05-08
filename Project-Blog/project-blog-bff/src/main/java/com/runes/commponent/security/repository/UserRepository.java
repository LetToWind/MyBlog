package com.runes.commponent.security.repository;

import com.runes.commponent.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByUsername(String username);
}
