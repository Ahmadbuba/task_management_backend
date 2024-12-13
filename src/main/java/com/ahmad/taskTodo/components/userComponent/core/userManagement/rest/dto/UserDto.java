package com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank
    @Email
    private String email;
}
