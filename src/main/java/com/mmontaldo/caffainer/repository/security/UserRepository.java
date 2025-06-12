package com.mmontaldo.caffainer.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmontaldo.caffainer.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(Long id);
}
