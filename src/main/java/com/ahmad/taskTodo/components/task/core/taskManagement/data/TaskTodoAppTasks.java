package com.ahmad.taskTodo.components.task.core.taskManagement.data;

import org.springframework.data.domain.Page;

import com.ahmad.taskTodo.components.task.core.taskManagement.database.entity.TaskEntity;
import com.ahmad.taskTodo.components.task.core.taskManagement.mapper.TaskDataMapper;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResources;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResources;

import lombok.Data;

@Data
public class TaskTodoAppTasks {
    final Page<TaskEntity> entityListPage;

    public TaskResources getResource() {
        return TaskDataMapper.INSTANCE.entityListPageToResources(this.entityListPage);
    }
}
