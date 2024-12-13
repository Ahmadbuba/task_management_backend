package com.ahmad.taskTodo.components.userComponent.core.userManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ahmad.taskTodo.components.userComponent.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.userComponent.core.userManagement.rest.resource.UserResource;

@Mapper
public interface UserDataMapper {
    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    UserResource entityToResource(UserEntity entity);
}
