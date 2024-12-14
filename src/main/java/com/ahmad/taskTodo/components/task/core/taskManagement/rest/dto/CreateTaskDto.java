package com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private int priority;
    @NotNull
    private ZonedDateTime dueDate;
}
