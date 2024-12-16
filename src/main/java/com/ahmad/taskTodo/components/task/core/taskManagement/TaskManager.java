package com.ahmad.taskTodo.components.task.core.taskManagement;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.taskTodo.components.task.core.taskManagement.data.TaskTodoAppTask;
import com.ahmad.taskTodo.components.task.core.taskManagement.data.TaskTodoAppTasks;
import com.ahmad.taskTodo.components.task.core.taskManagement.database.entity.TaskEntity;
import com.ahmad.taskTodo.components.task.core.taskManagement.database.repository.TaskRepository;
import com.ahmad.taskTodo.components.task.core.taskManagement.exception.NoSuchTaskFoundException;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto.CreateTaskDto;
import com.ahmad.taskTodo.components.user.core.userManagement.UserManager;
import com.ahmad.taskTodo.components.user.core.userManagement.data.TaskTodoAppUser;
import com.ahmad.taskTodo.components.user.core.userManagement.exception.UserNotFoundException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskManager {

    private final TaskRepository taskRepository;
    private final UserManager userManager;

    public TaskTodoAppTasks getAllUserTasks(final Authentication authentication, @NonNull final Pageable pageable) {
        final Page<TaskEntity> taskEntityPage;
        if (authentication == null) {
            taskEntityPage = this.taskRepository.findAll(pageable);
        }
        else {
            taskEntityPage = this.taskRepository.findAllByUserName(authentication.getName(), pageable);
        }
        return new TaskTodoAppTasks(taskEntityPage);
    }

    @Transactional
    public TaskTodoAppTask createTask(@NonNull final String username, final String title, final String description, final int priority, final ZonedDateTime dueDate) throws UserNotFoundException {
        final TaskTodoAppUser user = this.userManager.getUserByEmail(username);
        final TaskEntity newEntity = TaskEntity.create(user.getId(), user.getEmail(), title, description, priority, dueDate);
        this.taskRepository.save(newEntity);
        return new TaskTodoAppTask(newEntity);
    }

    public TaskTodoAppTask getTask(final String userEmail, @NonNull final UUID taskId) {
        final TaskEntity entity;
        if (userEmail == null || userEmail.isEmpty()) {
            entity = this.taskRepository.findById(taskId).orElse(null);
        }
        else {
            entity = this.taskRepository.findByIdAndUserName(taskId, userEmail).orElse(null);
        }
        if (entity == null) {
            return null;
        }
        return new TaskTodoAppTask(entity);
    }

    @Transactional
    public TaskTodoAppTask updateTask(final String username, @NonNull final UUID taskId, final String title, final String description, final int priority, final ZonedDateTime dueDate) throws NoSuchTaskFoundException {
        final TaskEntity entity;
        if (username == null || username.isEmpty()) {
            entity = this.taskRepository.findById(taskId).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        else {
            entity = this.taskRepository.findByIdAndUserName(taskId, username).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setPriority(priority);
        entity.setDueDate(dueDate);
        this.taskRepository.save(entity);
        return new TaskTodoAppTask(entity);
    }

    @Transactional
    public void deleteTask(final String username, @NonNull final UUID taskId) {
        if (username == null || username.isEmpty()) {
            this.taskRepository.deleteById(taskId);
        }
        this.taskRepository.deleteByIdAndUserName(taskId, username);
    }

    @Transactional
    public TaskTodoAppTask setTaskComplete(final String username, @NonNull final UUID taskId) throws NoSuchTaskFoundException {
        final TaskEntity entity;
        if (username == null || username.isEmpty()) {
            entity = this.taskRepository.findById(taskId).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        else {
            entity = this.taskRepository.findByIdAndUserName(taskId, username).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        entity.setCompleted(true);
        this.taskRepository.save(entity);
        return new TaskTodoAppTask(entity);
    }


    @Transactional
    public TaskTodoAppTask setTaskIncomplete(final String username, @NonNull final UUID taskId) throws NoSuchTaskFoundException {
        final TaskEntity entity;
        if (username == null || username.isEmpty()) {
            entity = this.taskRepository.findById(taskId).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        else {
            entity = this.taskRepository.findByIdAndUserName(taskId, username).orElseThrow(() -> new NoSuchTaskFoundException("Task was not found"));
        }
        entity.setCompleted(false);
        this.taskRepository.save(entity);
        return new TaskTodoAppTask(entity);
    }

    /*
    * endpoint to get complete/incomplete task, by dueDate, by priority, by description
    * or even a combination: Returns a list of task: TaskTodoAppTasks
    * getAllTasks(@NonNull final Page page): TaskTodoAppTasks
    * */

    /*
     * endpoint to get user complete/incomplete task, by dueDate, by priority, by description
     * or even a combination: Returns a list of task: TaskTodoAppTasks
     * getAllUserTasks(@NonNull final String userEmail, @NonNull final Page page): TaskTodoAppTasks
     * */

    /*
    * endpoint to maybe get uncompleted task past due date
    * endpoint to maybe get complete task within certain date
    * in general i should have strong sorting capabilities
    * */
}
