package com.ahmad.taskTodo.components.security.core.tokenManagement.data;

import com.ahmad.taskTodo.components.security.core.tokenManagement.mapper.TokenDataMapper;
import com.ahmad.taskTodo.components.security.core.tokenManagement.rest.resource.TokenResource;

import lombok.Data;

@Data
public class TaskTodoAppSecurityToken {
    private String issuer;
    private String accessToken;
    private String refreshToken;

    public TokenResource toTokenResource() {
        return TokenDataMapper.INSTANCE.dataToResource(this);
    }
}
