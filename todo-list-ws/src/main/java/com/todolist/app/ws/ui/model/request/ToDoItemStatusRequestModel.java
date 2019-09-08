package com.todolist.app.ws.ui.model.request;

import com.todolist.app.ws.shared.StatusEnum;

public class ToDoItemStatusRequestModel {

	private String todoItemId;
	private StatusEnum status;

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

}
