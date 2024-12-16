package com.ahmad.taskTodo.components.task.core.taskManagement;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ahmad.taskTodo.common.restException.TaskTodoAppResourceNotFoundException;
import com.ahmad.taskTodo.components.task.core.taskManagement.data.TaskTodoAppTasks;
import com.ahmad.taskTodo.components.task.core.taskManagement.data.TaskTodoAppTask;
import com.ahmad.taskTodo.components.task.core.taskManagement.exception.NoSuchTaskFoundException;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto.CreateTaskDto;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto.TaskDto;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResource;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResources;
import com.ahmad.taskTodo.components.user.core.userManagement.exception.UserNotFoundException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskDataService {
    private final TaskManager taskManager;

    public TaskResources getAllUserTasks(final Authentication authentication, @NonNull final Pageable pageable) {
        return this.taskManager.getAllUserTasks(authentication,pageable)
                 .getResource();
    }

    public TaskResource createTask(final Authentication authentication, final CreateTaskDto createTaskDto) {
        try {
            return this.taskManager.createTask(authentication.getName(), createTaskDto.getTitle(), createTaskDto.getDescription(), createTaskDto.getPriority(), createTaskDto.getDueDate())
                                   .getResource();
        }
        catch (final UserNotFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e.getMessage());
        }
    }

    public TaskResource getTask(@NonNull final Authentication authentication, @NonNull final String taskId) {
        final TaskTodoAppTask task = this.taskManager.getTask(authentication.getName(), UUID.fromString(taskId));
        if (task == null) {
            throw new TaskTodoAppResourceNotFoundException("Task not found");
        }
         return task.getResource();
    }

    public TaskResource updateTask(@NonNull final Authentication authentication, @NonNull final String taskId, @NonNull final TaskDto taskDto) {
        try {
            return this.taskManager.updateTask(authentication.getName(),UUID.fromString(taskId),taskDto.getTitle(),taskDto.getDescription(),taskDto.getPriority(),taskDto.getDueDate())
                     .getResource();
        }
        catch (final NoSuchTaskFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e.getMessage());
        }
    }

    public void deleteTask(@NonNull final Authentication authentication, @NonNull final String taskId) {
        this.taskManager.deleteTask(authentication.getName(), UUID.fromString(taskId));
    }

    public TaskResource setTaskComplete(@NonNull final Authentication authentication, @NonNull final String taskId) {
        try {
            return this.taskManager.setTaskComplete(authentication.getName(),UUID.fromString(taskId))
                     .getResource();
        }
        catch (final NoSuchTaskFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e.getMessage());
        }
    }

    public TaskResource setTaskIncomplete(@NonNull final Authentication authentication, @NonNull final String taskId) {
        try {
            return this.taskManager.setTaskIncomplete(authentication.getName(),UUID.fromString(taskId))
                                   .getResource();
        }
        catch (final NoSuchTaskFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e.getMessage());
        }
    }
}
