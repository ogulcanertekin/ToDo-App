package com.todolist.app.ws.ui.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todolist.app.ws.shared.StatusEnum;

public class ToDoItemRequestModel {

	private String todolistId;
	private String name;
	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate deadline;

	private StatusEnum status;

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

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getTodolistId() {
		return todolistId;
	}

	public void setTodolistId(String todolistId) {
		this.todolistId = todolistId;
	}
}
