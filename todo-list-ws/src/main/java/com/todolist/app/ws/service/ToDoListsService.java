package com.todolist.app.ws.service;

import java.util.List;

import com.todolist.app.ws.io.entity.UserEntity;
import com.todolist.app.ws.shared.dto.ToDoListDto;

public interface ToDoListsService {

	List<ToDoListDto> getUserTodoLists(String userId);

	ToDoListDto getToDoList(String todoListId);

	ToDoListDto createToDoList(ToDoListDto todolist, UserEntity user);
}
