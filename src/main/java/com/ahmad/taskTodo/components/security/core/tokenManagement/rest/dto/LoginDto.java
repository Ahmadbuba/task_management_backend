package com.ahmad.taskTodo.components.security.core.tokenManagement.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
