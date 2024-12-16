package com.ahmad.taskTodo.common.restException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TaskTodoAppConflictException extends TaskTodoAppRestException {
    public TaskTodoAppConflictException(final String errorCode, final Throwable cause) {
        super(HttpStatus.CONFLICT, errorCode, cause);
    }

    public TaskTodoAppConflictException(String msg, String errorCode, Object... args) {
        super(HttpStatus.CONFLICT, errorCode, String.format(msg, args));
    }
}
