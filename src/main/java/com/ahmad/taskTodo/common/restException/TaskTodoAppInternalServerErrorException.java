/*
 *  Author: DI Rainer Sickinger
 *  Date: 18.11.22, 17:21
 *  Contact: rainer.sickinger@heyrise.com
 *
 */

package com.ahmad.taskTodo.common.restException;

import org.springframework.http.HttpStatus;

public class TaskTodoAppInternalServerErrorException extends TaskTodoAppRestException {
    public TaskTodoAppInternalServerErrorException(Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, null, cause);
    }

    public TaskTodoAppInternalServerErrorException(final String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, null, msg);
    }
}
