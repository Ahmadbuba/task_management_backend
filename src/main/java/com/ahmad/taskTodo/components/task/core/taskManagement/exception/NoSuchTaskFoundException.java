package com.ahmad.taskTodo.components.task.core.taskManagement.exception;

import com.ahmad.taskTodo.common.exception.TaskTodoAppException;

public class NoSuchTaskFoundException extends TaskTodoAppException {

    public NoSuchTaskFoundException(final String msg) {
        super(msg);
    }

    public NoSuchTaskFoundException(final String msg, final Object... args) {
        super(msg, args);
    }

    public NoSuchTaskFoundException(final Exception cause) {
        super(cause);
    }

}
