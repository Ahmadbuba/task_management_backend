package com.ahmad.taskTodo.components.user.core.userManagement.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.ahmad.taskTodo.components.user.core.userManagement.data.TaskTodoAppUsers;
import com.ahmad.taskTodo.components.user.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResource;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResources;

@Mapper
public interface UserDataMapper {
    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    UserResource entityToResource(UserEntity entity);

    default UserResources entityListPageToResources(final Page<UserEntity> entityListPage) {
        final List<UserResource> resources = new ArrayList<>();
        final List<UserEntity> entityList = entityListPage.getContent().stream().toList();
        for (final UserEntity entity : entityList) {
            final UserResource userResource = this.entityToResource(entity);
            resources.add(userResource);
        }

        final UserResources resourcePage = new UserResources();
        resourcePage.setUsers(resources);
        resourcePage.setSize(entityListPage.getSize());
        resourcePage.setNumber(entityListPage.getNumber());
        resourcePage.setTotalElements(entityListPage.getTotalElements());
        resourcePage.setTotalPages(entityListPage.getTotalPages());
        return resourcePage;
    }

}
