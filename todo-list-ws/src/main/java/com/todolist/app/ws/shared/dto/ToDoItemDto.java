package com.todolist.app.ws.shared.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todolist.app.ws.shared.StatusEnum;

public class ToDoItemDto {

	private long id;
	private String todoItemId;
	private String name;
	private String description;
	private String todoListId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate deadline;

	private StatusEnum status;

	private ToDoListDto todoListDetail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTodoItemId() {
		return todoItemId;
	}

	public void setTodoItemId(String todoItemId) {
		this.todoItemId = todoItemId;
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public ToDoListDto getTodoListDetail() {
		return todoListDetail;
	}

	public void setTodoListDetail(ToDoListDto todoListDetail) {
		this.todoListDetail = todoListDetail;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public String getTodoListId() {
		return todoListId;
	}

	public void setTodoListId(String todoListId) {
		this.todoListId = todoListId;
	}

}
