package com.ahmad.taskTodo.components.user.core.userManagement.rest.resource;

import java.time.Instant;

import com.ahmad.taskTodo.components.user.core.userManagement.data.TaskTodoAppRole;

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
    @NotNull
    private Instant creationDate;
    @NotNull
    private Instant lastModifiedDate;
    @NotNull
    private String createdBy;
    @NotNull
    private String lastModifiedBy;
}
