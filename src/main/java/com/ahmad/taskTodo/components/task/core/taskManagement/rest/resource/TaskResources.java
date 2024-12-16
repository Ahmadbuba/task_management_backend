package com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource;

import java.util.List;

import lombok.Data;

@Data
public class TaskResources {
    private List<TaskResource> tasks;
    private int size;
    private int number;
    private long totalElements;
    private int totalPages;
}
