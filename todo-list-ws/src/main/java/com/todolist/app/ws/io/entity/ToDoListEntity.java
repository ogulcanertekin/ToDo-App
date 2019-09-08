package com.todolist.app.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "todolists")
public class ToDoListEntity implements Serializable {

	private static final long serialVersionUID = 5849649716377121924L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String todoListId;

	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	@OneToMany(mappedBy = "toDoListDetails", cascade = CascadeType.ALL)
	private List<ToDoItemEntity> todoitems;

	public String getTodoListId() {
		return todoListId;
	}

	public void setTodoListId(String todoListId) {
		this.todoListId = todoListId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	public List<ToDoItemEntity> getTodoItems() {
		return todoitems;
	}

	public void setTodoItems(List<ToDoItemEntity> todoitems) {
		this.todoitems = todoitems;
	}

}
