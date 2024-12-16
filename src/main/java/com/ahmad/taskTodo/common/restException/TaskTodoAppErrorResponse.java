/*
 *  Author: DI Rainer Sickinger
 *  Date: 02.12.22, 12:37
 *  Contact: rainer.sickinger@heyrise.com
 *
 */

package com.ahmad.taskTodo.common.restException;

import lombok.Data;

@Data
public class TaskTodoAppErrorResponse {
    private String errorCode;

    private String message;
}
