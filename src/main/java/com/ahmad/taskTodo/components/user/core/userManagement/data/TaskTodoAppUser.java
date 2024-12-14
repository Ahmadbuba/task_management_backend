package com.ahmad.taskTodo.components.user.core.userManagement.data;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.ahmad.taskTodo.components.user.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.user.core.userManagement.mapper.UserDataMapper;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskTodoAppUser {
    private UserEntity entity;

    @NotNull
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private TaskTodoAppRole role;
    @NotNull
    private boolean active;
    @NotNull
    private Instant creationDate;
    @NotNull
    private Instant lastModifiedDate;
    @NotNull
    private Instant createdBy;
    @NotNull
    private Instant lastModifiedBy;

    public TaskTodoAppUser(final UserEntity entity) {
        this.entity = entity;
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.active = entity.isActive();
    }

    public UserResource getResource() {
        return UserDataMapper.INSTANCE.entityToResource(this.entity);
    }

}
