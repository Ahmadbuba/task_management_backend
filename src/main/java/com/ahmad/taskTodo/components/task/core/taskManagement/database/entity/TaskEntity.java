package com.ahmad.taskTodo.components.task.core.taskManagement.database.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
* Make entity all entity classes auditable and add
* corresponding fields in data classes
* */
@Entity
@Data
public class TaskEntity {

    @Id
    private UUID id;

    private String title;

    private String description;

    private int priority;

    private ZonedDateTime dueDate;

    private boolean completed;


    protected TaskEntity() {}

    public static TaskEntity create(final String title, final String description, final int priority, final ZonedDateTime dueDate) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(description, "Description cannot be null");
        Objects.requireNonNull(dueDate, "Due date cannot be null");
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.id = UUID.randomUUID();
        taskEntity.title = title;
        taskEntity.description = description;
        taskEntity.priority = priority;
        taskEntity.dueDate = dueDate;
        taskEntity.completed = false;
        return taskEntity;
    }


    /*
    * potentially create more static constructors to match various parameters
    * */


}
