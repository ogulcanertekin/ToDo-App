package com.todolist.app.ws.ui.model.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todolist.app.ws.shared.StatusEnum;

public class ToDoItemRest {

	private String todoListId;

	private String name;

	private String description;

	private String todoItemId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate deadline;

	private StatusEnum status;

	public String getTodoListId() {
		return todoListId;
	}

	public void setTodoListId(String todoListId) {
		this.todoListId = todoListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTodoItemId() {
		return todoItemId;
	}

	public void setTodoItemId(String todoItemId) {
		this.todoItemId = todoItemId;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

}
