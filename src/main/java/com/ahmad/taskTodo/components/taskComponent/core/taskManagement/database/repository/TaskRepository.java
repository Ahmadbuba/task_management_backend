package com.ahmad.taskTodo.components.taskComponent.core.taskManagement.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.database.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {}
