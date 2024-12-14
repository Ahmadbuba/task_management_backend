package com.ahmad.taskTodo.components.task.core.taskManagement.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.taskTodo.components.task.core.taskManagement.database.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {}
