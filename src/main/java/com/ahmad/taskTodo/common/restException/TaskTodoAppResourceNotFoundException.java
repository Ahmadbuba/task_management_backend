/*
 *  Author: DI Rainer Sickinger
 *  Date: 18.11.22, 17:21
 *  Contact: rainer.sickinger@heyrise.com
 *
 */

package com.ahmad.taskTodo.common.restException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskTodoAppResourceNotFoundException extends TaskTodoAppRestException {
    public TaskTodoAppResourceNotFoundException(final Throwable cause) {
        super(HttpStatus.NOT_FOUND, null, cause);
    }

    public TaskTodoAppResourceNotFoundException(String msg, Object... args) {
        super(HttpStatus.NOT_FOUND, null, String.format(msg, args));
    }
}
