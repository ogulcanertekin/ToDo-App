package com.todolist.app.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todolist.app.ws.shared.StatusEnum;

@Entity(name = "todoitems")
public class ToDoItemEntity implements Serializable {

	private static final long serialVersionUID = 8319370541491260508L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String todoItemId;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 200, nullable = false)
	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate deadline;

	private StatusEnum status;

	@ManyToOne
	@JoinColumn(name = "todolists_id")
	private ToDoListEntity toDoListDetails;

	public String getTodoItemId() {
		return todoItemId;
	}

	public void setTodoItemId(String todoItemId) {
		this.todoItemId = todoItemId;
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

	public ToDoListEntity getToDoListDetails() {
		return toDoListDetails;
	}

	public void setToDoListDetails(ToDoListEntity toDoListDetails) {
		this.toDoListDetails = toDoListDetails;
	}

}
