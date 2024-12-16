package com.ahmad.taskTodo.components.security.core.tokenManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ahmad.taskTodo.components.security.core.tokenManagement.data.TaskTodoAppSecurityToken;
import com.ahmad.taskTodo.components.security.core.tokenManagement.rest.resource.TokenResource;


@Mapper
public interface TokenDataMapper {
    TokenDataMapper INSTANCE = Mappers.getMapper(TokenDataMapper.class);

    TokenResource dataToResource(TaskTodoAppSecurityToken data);
}
