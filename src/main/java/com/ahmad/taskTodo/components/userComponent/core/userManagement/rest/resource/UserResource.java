package com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.resource;

import com.ahmad.taskTodo.components.userComponent.core.userManagement.data.TaskTodoAppRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserResource {
    @NotNull
    private String name;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    private TaskTodoAppRole role;
    @NotNull
    private boolean active;
}
