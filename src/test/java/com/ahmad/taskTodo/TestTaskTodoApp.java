package com.ahmad.taskTodo;

import org.springframework.boot.SpringApplication;

public class TestTaskTodoApp {

	public static void main(String[] args) {
		SpringApplication.from(TaskTodoApp::main).with(TestcontainersConfiguration.class).run(args);
	}

}
