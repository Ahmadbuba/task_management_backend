package com.ahmad.taskTodo.components.taskComponent.core.taskManagement.data;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.database.entity.TaskEntity;
import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.mapper.TaskDataMapper;
import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.rest.resource.TaskResource;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
* Add auditable fields
* */
@Data
public class TaskTodoAppTask {
    private TaskEntity entity;

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

    public TaskTodoAppTask(final TaskEntity entity) {
        this.entity = entity;
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.priority = entity.getPriority();
        this.dueDate = entity.getDueDate();
        this.completed = entity.isCompleted();
    }

    public TaskResource getResource() {
        return TaskDataMapper.INSTANCE.entityToResource(this.entity);
    }
}
