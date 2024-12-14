package com.ahmad.taskTodo.components.user.core.userManagement.rest.resource;

import java.util.List;

import lombok.Data;

@Data
public class UserResources {
    // takes a page of user entity
    private List<UserResource> users;
    private int size;
    private int number;
    private long totalElements;
    private int totalPages;
}
