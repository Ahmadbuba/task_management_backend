package com.ahmad.taskTodo.components.taskComponent.core.taskManagement.rest.dto;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private int priority;
    @NotNull
    private ZonedDateTime dueDate;
}
