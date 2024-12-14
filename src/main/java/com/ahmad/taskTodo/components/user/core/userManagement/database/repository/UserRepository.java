package com.ahmad.taskTodo.components.user.core.userManagement.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.taskTodo.components.user.core.userManagement.database.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
