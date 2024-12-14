package com.ahmad.taskTodo.components.task.core.taskManagement.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.taskTodo.components.task.core.taskManagement.database.entity.TaskEntity;

import lombok.NonNull;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    Optional<TaskEntity> findByIdAndUserName(final UUID id, final String userName);

    void deleteByIdAndUserName(final UUID id, final String userName);

    Page<TaskEntity> findAllByUserName(@NonNull final String userName, Pageable pageable);

}
