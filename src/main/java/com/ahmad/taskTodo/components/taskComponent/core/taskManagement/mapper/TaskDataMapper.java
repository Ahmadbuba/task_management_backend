package com.ahmad.taskTodo.components.taskComponent.core.taskManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.database.entity.TaskEntity;
import com.ahmad.taskTodo.components.taskComponent.core.taskManagement.rest.resource.TaskResource;

@Mapper
public interface TaskDataMapper {
    TaskDataMapper INSTANCE = Mappers.getMapper(TaskDataMapper.class);

    TaskResource entityToResource(TaskEntity entity);
}
