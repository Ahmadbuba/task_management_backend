package com.ahmad.taskTodo.components.userComponent.core.userManagement.exception;

import com.ahmad.taskTodo.common.exception.TaskTodoAppException;

public class UserValidationException extends TaskTodoAppException {
    public UserValidationException(final String msg) {
        super(msg);
    }

    public UserValidationException(final String msg, final Object... args) {
        super(msg, args);
    }

    public UserValidationException(final Exception cause) {
        super(cause);
    }
}
