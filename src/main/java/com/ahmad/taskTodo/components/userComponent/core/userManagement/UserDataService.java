package com.ahmad.taskTodo.components.userComponent.core.userManagement;

import org.springframework.stereotype.Service;

import com.ahmad.taskTodo.common.restException.TaskTodoAppConflictException;
import com.ahmad.taskTodo.common.restException.TaskTodoAppResourceNotFoundException;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.exception.UserExistsException;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.exception.UserNotFoundException;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.dto.CreateUserDto;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.dto.UpdateUserDto;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.dto.UserDto;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.resource.UserResource;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserManager userManager;

    public UserResource createUser(@NonNull final CreateUserDto createUserDto) {
        try {
            return this.userManager.createUser(createUserDto.getName(), createUserDto.getEmail(), createUserDto.getPassword())
                                   .getResource();
        }
        catch (final UserExistsException e) {
            throw new TaskTodoAppConflictException("409", e);
        }
    }

    public UserResource getUser(@NonNull final UserDto userDto) {
        try {
            return this.userManager.getUserByEmail(userDto.getEmail())
                                   .getResource();
        }
        catch (final UserNotFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e);
        }

    }

    public UserResource updateUser(@NonNull final UpdateUserDto updateUserDto) {
        try {
            return this.userManager.updateUser(updateUserDto.getName(), updateUserDto.getEmail())
                                   .getResource();
        }
        catch (final UserNotFoundException e) {
            throw new TaskTodoAppResourceNotFoundException(e);
        }
    }

    public void deleteUser(@NonNull final UserDto userDto) {
        this.userManager.deleteUser(userDto.getEmail());
    }
}