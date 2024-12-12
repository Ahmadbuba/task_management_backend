package com.ahmad.taskTodo.common.exception;

public abstract class TaskTodoAppException extends Exception{
    public TaskTodoAppException(String msg) {
        super(msg);
    }

    public TaskTodoAppException(String msg, Object... args) {
        super(String.format(msg, args));
    }

    public TaskTodoAppException(Exception cause) {
        super(cause);
    }
}
