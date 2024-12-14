package com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskResource {
    @NotNull
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private int priority;
    @NotNull
    private ZonedDateTime dueDate;
    @NotNull
    private boolean completed;
}
