package com.ahmad.taskTodo.components.security.core.tokenManagement.rest.resource;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenResource {
    @NotBlank
    private String issuer;
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
