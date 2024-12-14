package com.ahmad.taskTodo.components.user.core.userManagement.exception;

import com.ahmad.taskTodo.common.exception.TaskTodoAppException;

public class UserNotFoundException extends TaskTodoAppException {

    public UserNotFoundException(final String msg) {
        super(msg);
    }

    public UserNotFoundException(final String msg, final Object... args) {
        super(msg, args);
    }

    public UserNotFoundException(final Exception cause) {
        super(cause);
    }

}
