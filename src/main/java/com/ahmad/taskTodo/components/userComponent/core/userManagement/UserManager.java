package com.ahmad.taskTodo.components.userComponent.core.userManagement;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ahmad.taskTodo.components.userComponent.core.userManagement.data.TaskTodoAppUser;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.database.repository.UserRepository;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.exception.UserExistsException;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.exception.UserNotFoundException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public TaskTodoAppUser createUser(@NonNull final String name, @NonNull final String email, @NonNull final String password) throws UserExistsException {
        if (this.repository.existsByEmail(email)) {
            throw new UserExistsException("User with email " + email + " already exists");
        }
        final UserEntity entity = UserEntity.createUser(name, email, this.passwordEncoder.encode(password));
        this.repository.save(entity);
        return new TaskTodoAppUser(entity);
    }


    public TaskTodoAppUser getUserById(@NonNull final UUID id) throws UserNotFoundException {
        final UserEntity entity = this.repository.findById(id).orElseThrow(
          () -> new UserNotFoundException("User with id " + id + " not found."));
        return new TaskTodoAppUser(entity);

    }


    public TaskTodoAppUser getUserByEmail(@NonNull final String email) throws UserNotFoundException {
        final UserEntity entity = this.repository.findByEmail(email).orElseThrow(
          () -> new UserNotFoundException("User with email " + email + " not found."));
        return new TaskTodoAppUser(entity);
    }


    public TaskTodoAppUser updateUser(final String name, @NonNull final String email) throws UserNotFoundException {
        final UserEntity entity = this.repository.findByEmail(email).orElseThrow(
                                                    () -> new UserNotFoundException("User with email " + email + " not found"));
        entity.setName(name);
        entity.setEmail(email);
        return new TaskTodoAppUser(entity);
    }


    public TaskTodoAppUser updateUserPassword(@NonNull final UUID id, @NonNull final String password) throws UserNotFoundException {
        final UserEntity entity = this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        entity.setPassword(this.passwordEncoder.encode(password));
        this.repository.save(entity);
        return new TaskTodoAppUser(entity);
    }


    public TaskTodoAppUser updateUserPassword(@NonNull final String email, @NonNull String password) throws UserNotFoundException {
        final UserEntity entity = this.repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        entity.setPassword(this.passwordEncoder.encode(password));
        this.repository.save(entity);
        return new TaskTodoAppUser(entity);
    }


    public void deleteUser(@NonNull final UUID id) {
        this.repository.deleteById(id);
    }


    public void deleteUser(@NonNull final String email) {
        this.repository.deleteByEmail(email);
    }

}
