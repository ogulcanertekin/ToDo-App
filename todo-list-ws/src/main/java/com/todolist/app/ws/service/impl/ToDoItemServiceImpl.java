package com.todolist.app.ws.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.app.ws.exceptions.UserServiceException;
import com.todolist.app.ws.io.entity.ToDoItemEntity;
import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.io.repositories.TodoItemRepository;
import com.todolist.app.ws.io.repositories.TodoListRepository;
import com.todolist.app.ws.service.ToDoItemService;
import com.todolist.app.ws.shared.StatusEnum;
import com.todolist.app.ws.shared.Utils;
import com.todolist.app.ws.shared.dto.ToDoItemDto;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.ui.model.request.ToDoItemStatusRequestModel;
import com.todolist.app.ws.ui.model.response.ErrorMessages;

@Service
public class ToDoItemServiceImpl implements ToDoItemService {

	@Autowired
	TodoItemRepository todoitemRepository;

	@Autowired
	TodoListRepository todolistRepository;

	@Autowired
	Utils utils;

	@Override
	public ToDoListDto getToDoList(String todoListId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToDoItemDto createToDoItem(ToDoItemDto todoitem, ToDoListEntity todoList) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		ToDoItemEntity todoitemEntity = modelMapper.map(todoitem, ToDoItemEntity.class);

		String publicToDoItemId = utils.generateUserId(30);
		todoitemEntity.setTodoItemId(publicToDoItemId);

		todoitemEntity.setToDoListDetails(todoList);

		todoitemEntity.setStatus(StatusEnum.Uncompleted);

		ToDoItemEntity storedItemDetails = todoitemRepository.save(todoitemEntity);

		ToDoItemDto returnValue = modelMapper.map(storedItemDetails, ToDoItemDto.class);
		;

		return returnValue;

	}

	@Override
	public ToDoItemDto findByName(String name) {
		ToDoItemEntity todoitem = todoitemRepository.findByName(name);
		ModelMapper modelMapper = new ModelMapper();

		ToDoItemDto todoitemDto = modelMapper.map(todoitem, ToDoItemDto.class);

		return todoitemDto;
	}

	@Override
	public List<ToDoItemDto> getToDoItemsOfList(String todolistId) {
		List<ToDoItemDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		ToDoListEntity todoListEntity = todolistRepository.findByTodoListId(todolistId);

		if (todoListEntity == null)
			return returnValue;

		Iterable<ToDoItemEntity> todolistitems = todoitemRepository.findAllBytoDoListDetails(todoListEntity);

		for (ToDoItemEntity todoitemtEntity : todolistitems) {
			returnValue.add(modelMapper.map(todoitemtEntity, ToDoItemDto.class));
		}

		LocalDate datenow = LocalDate.now();

		for (int i = 0; i < returnValue.size(); i++) {
			ToDoItemDto todoItem = returnValue.get(i);
			LocalDate deadline = todoItem.getDeadline();

			if (todoItem.getStatus() != StatusEnum.Completed)

				if (datenow.isAfter(deadline))
					todoItem.setStatus(StatusEnum.Expired);
				else if (datenow.isBefore(deadline))
					todoItem.setStatus(StatusEnum.Uncompleted);

				else
					todoItem.setStatus(StatusEnum.Uncompleted);

		}

		return returnValue;
	}

	@Override
	public ToDoItemDto updateToDoItemStatus(ToDoItemStatusRequestModel todoitemStatusRequest) {

		StatusEnum itemstatus = todoitemStatusRequest.getStatus();

		ModelMapper modelMapper = new ModelMapper();
		ToDoItemEntity todoitemEntity = todoitemRepository.findByTodoItemId(todoitemStatusRequest.getTodoItemId());

		if (todoitemEntity.getStatus() == StatusEnum.Uncompleted)
			todoitemEntity.setStatus(itemstatus);
		else
			throw new UserServiceException(ErrorMessages.COULD_NOT_UPDATE_TODO_ITEM_RECORD.getErrorMessage());

		ToDoItemDto todoitemDto = modelMapper.map(todoitemEntity, ToDoItemDto.class);

		todoitemRepository.save(todoitemEntity);

		return todoitemDto;

	}

}
