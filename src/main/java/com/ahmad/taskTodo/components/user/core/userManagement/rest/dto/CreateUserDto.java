package com.ahmad.taskTodo.components.user.core.userManagement.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotNull
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
