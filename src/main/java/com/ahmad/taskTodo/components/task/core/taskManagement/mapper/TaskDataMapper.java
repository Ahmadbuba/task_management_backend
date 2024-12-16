package com.ahmad.taskTodo.components.task.core.taskManagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.ahmad.taskTodo.components.task.core.taskManagement.database.entity.TaskEntity;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResource;
import com.ahmad.taskTodo.components.task.core.taskManagement.rest.resource.TaskResources;

@Mapper
public interface TaskDataMapper {
    TaskDataMapper INSTANCE = Mappers.getMapper(TaskDataMapper.class);

    TaskResource entityToResource(TaskEntity entity);

    default TaskResources entityListPageToResources(final Page<TaskEntity> entityListPage) {
        final List<TaskResource> resources = new ArrayList<>();
        final List<TaskEntity> entityList = entityListPage.getContent().stream().toList();
        for (final TaskEntity entity : entityList) {
            final TaskResource resource = this.entityToResource(entity);
            resources.add(resource);
        }

        final TaskResources resourcePage = new TaskResources();
        resourcePage.setTasks(resources);
        resourcePage.setSize(entityListPage.getSize());
        resourcePage.setNumber(entityListPage.getNumber());
        resourcePage.setTotalElements(entityListPage.getTotalElements());
        resourcePage.setTotalPages(entityListPage.getTotalPages());
        return resourcePage;
    }

}
