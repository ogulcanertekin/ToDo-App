package com.todolist.app.ws.ui.model.response;

import java.util.List;

import com.todolist.app.ws.shared.dto.ToDoItemDto;

public class ToDoListsRest {

	private String userId;
	private String todoListId;
	private String name;
	private List<ToDoItemDto> todoitems;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public List<ToDoItemDto> getTodoitems() {
		return todoitems;
	}

	public void setTodoitems(List<ToDoItemDto> todoitems) {
		this.todoitems = todoitems;
	}

}
