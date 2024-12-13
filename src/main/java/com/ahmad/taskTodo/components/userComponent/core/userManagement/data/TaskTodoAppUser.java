package com.ahmad.taskTodo.components.userComponent.core.userManagement.data;

import java.util.UUID;

import com.ahmad.taskTodo.components.userComponent.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.mapper.UserDataMapper;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.resource.UserResource;

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
