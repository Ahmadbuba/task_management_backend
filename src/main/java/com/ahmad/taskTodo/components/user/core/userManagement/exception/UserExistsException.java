package com.ahmad.taskTodo.components.user.core.userManagement.exception;

import com.ahmad.taskTodo.common.exception.TaskTodoAppException;

public class UserExistsException extends TaskTodoAppException {

    public UserExistsException(final String msg) {
        super(msg);
    }

    public UserExistsException(final String msg, final Object... args) {
        super(msg, args);
    }

    public UserExistsException(final Exception cause) {
        super(cause);
    }

}
