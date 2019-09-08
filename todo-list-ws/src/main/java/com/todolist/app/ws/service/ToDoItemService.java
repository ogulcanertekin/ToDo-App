package com.todolist.app.ws.service;

import java.util.List;

import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.shared.dto.ToDoItemDto;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.ui.model.request.ToDoItemStatusRequestModel;

public interface ToDoItemService {

	ToDoItemDto updateToDoItemStatus(ToDoItemStatusRequestModel todoitemStatusRequest);

	ToDoListDto getToDoList(String todoListId);

	ToDoItemDto findByName(String name);

	ToDoItemDto createToDoItem(ToDoItemDto todoitem, ToDoListEntity todoList);

	List<ToDoItemDto> getToDoItemsOfList(String todoListId);

}
