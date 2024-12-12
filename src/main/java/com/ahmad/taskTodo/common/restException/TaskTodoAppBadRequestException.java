package com.ahmad.taskTodo.common.restException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaskTodoAppBadRequestException extends TaskTodoAppRestException {
    public TaskTodoAppBadRequestException(final Throwable cause) {
        super(HttpStatus.BAD_REQUEST, null, cause);
    }

    public TaskTodoAppBadRequestException(String msg) {
        super(HttpStatus.BAD_REQUEST, null, msg);
    }

    public TaskTodoAppBadRequestException(String msg, Object... args) {
        super(HttpStatus.BAD_REQUEST, null, String.format(msg, args));
    }
}
