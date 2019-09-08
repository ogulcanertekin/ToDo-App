package com.todolist.app.ws.ui.model.request;

import java.util.List;

public class ToDoListRequestModel {

	private String userId;
	private String name;
	private List<ToDoItemRequestModel> todoitems;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ToDoItemRequestModel> getTodoitems() {
		return todoitems;
	}

	public void setTodoitems(List<ToDoItemRequestModel> todoitems) {
		this.todoitems = todoitems;
	}

	public List<ToDoItemRequestModel> getTodoItems() {
		return todoitems;
	}

	public void setTodoItems(List<ToDoItemRequestModel> todoitems) {
		this.todoitems = todoitems;
	}

}
