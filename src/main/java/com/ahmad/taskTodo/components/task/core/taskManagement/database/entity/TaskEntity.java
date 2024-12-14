package com.ahmad.taskTodo.components.task.core.taskManagement.database.entity;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.ahmad.taskTodo.common.audit.database.entity.Auditable;
import com.ahmad.taskTodo.components.user.core.userManagement.database.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
* Make entity all entity classes auditable and add
* corresponding fields in data classes
* */
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class TaskEntity extends Auditable {

    @Id
    private UUID id;

    private String title;

    private String description;

    private int priority;

    private ZonedDateTime dueDate;

    private boolean completed;

    private UUID userId;

    private String userName;


    protected TaskEntity() {
        super();
    }

    public static TaskEntity create(final UUID userId, final String userName, final String title, final String description, final int priority, final ZonedDateTime dueDate) {
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.id = UUID.randomUUID();
        taskEntity.title = title;
        taskEntity.description = description;
        taskEntity.priority = priority;
        taskEntity.dueDate = dueDate;
        taskEntity.completed = false;
        taskEntity.userId = userId;
        taskEntity.userName = userName;
        return taskEntity;
    }


    /*
    * potentially create more static constructors to match various parameters
    * */


    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    public String getLastModifiedBy() {
        return super.getLastModifiedBy();
    }

    public Instant getCreationDate() {
        return super.getCreationDate();
    }

    public Instant getLastModifiedDate() {
        return super.getLastModifiedDate();
    }

}
