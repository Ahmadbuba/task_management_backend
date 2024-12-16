package com.ahmad.taskTodo.components.security.core.tokenManagement.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    @Email(message = "Invalid mail address")
    private String email;
    @NotBlank
    @Size(min = 6, message = "Invalid Password")
    private String password;
}
