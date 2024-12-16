package com.ahmad.taskTodo.components.user.restController;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.taskTodo.components.user.core.userManagement.UserDataService;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.dto.CreateUserDto;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.dto.UpdateUserDto;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResource;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-management")
@Tag(name = "User Management API", description = "API's for managing users")
public class UserController {
    private final UserDataService userDataService;

    @Operation(
      description = "Register/Creates a user",
      summary = "Validates user credentials and returns authorization tokens"
    )
    @PostMapping("/register")
    public UserResource createUser(@Valid @RequestBody final CreateUserDto createUserDto) {
        return this.userDataService.createUser(createUserDto);
    }

    @Operation(
      description = "Get a user",
      summary = "Gets a user by using the provided email to search"
    )
    @GetMapping("/{mail}")
    public UserResource getUser(@PathVariable(name = "mail") @NotBlank final String email) {
        return this.userDataService.getUser(email);
    }


    @Operation(
      description = "Get authenticated user detail",
      summary = "Provides the details of the currently authenticated user"
    )
    @GetMapping("/me")
    public UserResource getMyself(final Authentication authentication) {
        return this.userDataService.getUser(authentication.getName());
    }


    @Operation(
      description = "Update a user"
    )
    @PutMapping
    public UserResource updateUser(@Valid @RequestBody final UpdateUserDto updateUserDto) {
        return this.userDataService.updateUser(updateUserDto);
    }

    @Operation(
      description = "Delete a user",
      summary = "Deletes the user with the given email"
    )
    @DeleteMapping("{mail}")
    public void deleteUser(@PathVariable(name = "mail") @NotBlank final String email) {
        this.userDataService.deleteUser(email);
    }


    @Operation(
      description = "Get users"
    )
    @GetMapping
    public UserResources getAllUsers(
      @RequestParam(defaultValue = "0") final int page,
      @RequestParam(defaultValue = "5") final int size,
      @RequestParam(defaultValue = "creationDate") final String sortBy,
      @RequestParam(defaultValue = "true") final boolean ascending
    ) {

        final Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        final Pageable pageable = PageRequest.of(page, size, sort);
        return this.userDataService.getAllUsers(pageable);
    }

}
