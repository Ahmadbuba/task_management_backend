package com.ahmad.taskTodo.components.user.core.userManagement.data;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ahmad.taskTodo.components.user.core.userManagement.database.entity.UserEntity;
import com.ahmad.taskTodo.components.user.core.userManagement.mapper.UserDataMapper;
import com.ahmad.taskTodo.components.user.core.userManagement.rest.resource.UserResources;

import lombok.Data;

@Data
public class TaskTodoAppUsers {
    final Page<UserEntity> entityListPage;

    public UserResources getResource() {
        return UserDataMapper.INSTANCE.entityListPageToResources(this.entityListPage);
    }

}
