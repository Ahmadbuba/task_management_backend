package com.ahmad.taskTodo.components.task.restController;

import java.security.Principal;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.taskTodo.components.task.core.taskManagement.TaskDataService;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto.CreateTaskDto;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.dto.TaskDto;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResource;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResources;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.dto.CreateUserDto;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResource;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-management")
@Tag(name = "Tasks Management API", description = "API's for managing tasks")
public class TaskController {
    private final TaskDataService taskDataService;

    @Operation(
      description = "Create a task"
    )
    @PostMapping
    public TaskResource createTask(final Authentication authentication, @Valid @RequestBody final CreateTaskDto createTaskDto) {
        return this.taskDataService.createTask(authentication,createTaskDto);
    }

    @Operation(
      description = "Get task"
    )
    @GetMapping("/{taskId}")
    public TaskResource getTask(final Authentication authentication, @PathVariable @NotBlank final String taskId) {
        return this.taskDataService.getTask(authentication,taskId);
    }

    @Operation(
      description = "update task"
    )
    @PutMapping("/{taskId}")
    public TaskResource updateTask(final Authentication authentication, @PathVariable @NotBlank final String taskId, @Valid @RequestBody final TaskDto taskDto) {
        return this.taskDataService.updateTask(authentication,taskId,taskDto);
    }

    @Operation(
      description = "delete task"
    )
    @DeleteMapping("/{taskId}")
    public void deleteTask(final Authentication authentication, @PathVariable @NotBlank final String taskId) {
        this.taskDataService.deleteTask(authentication,taskId);
    }

    @Operation(
      description = "set task complete"
    )
    @PutMapping("/{taskId}/complete")
    public TaskResource setTaskComplete(final Authentication authentication, @PathVariable @NotBlank final String taskId) {
        return this.taskDataService.setTaskComplete(authentication,taskId);
    }

    @Operation(
      description = "set task incomplete"
    )
    @PutMapping("/{taskId}/incomplete")
    public TaskResource setTaskIncomplete(final Authentication authentication, @PathVariable @NotBlank final String taskId) {
        return this.taskDataService.setTaskIncomplete(authentication,taskId);
    }


    @Operation(
      description = "Get all User tasks"
    )
    @GetMapping
    public TaskResources getAllUserTasks(
      final Authentication authentication,
      @RequestParam(defaultValue = "0") final int page,
      @RequestParam(defaultValue = "5") final int size,
      @RequestParam(defaultValue = "creationDate") final String sortBy,
      @RequestParam(defaultValue = "true") final boolean descending
    ) {

        final Sort sort = descending ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        final Pageable pageable = PageRequest.of(page, size, sort);
        return this.taskDataService.getAllUserTasks(authentication, pageable);
    }

}
