package com.todolist.app.ws.shared.dto;

import java.util.List;

public class ToDoListDto {

	private long id;
	private String todoListId;
	private String name;
	private UserDto userDetails;
	private List<ToDoItemDto> todoitems;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public UserDto getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}

	public List<ToDoItemDto> getTodoitems() {
		return todoitems;
	}

	public void setTodoitems(List<ToDoItemDto> todoitems) {
		this.todoitems = todoitems;
	}

}
