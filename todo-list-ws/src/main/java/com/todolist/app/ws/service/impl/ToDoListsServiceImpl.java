package com.todolist.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.io.entity.UserEntity;
import com.todolist.app.ws.io.repositories.TodoListRepository;
import com.todolist.app.ws.io.repositories.UserRepository;
import com.todolist.app.ws.service.ToDoListsService;
import com.todolist.app.ws.shared.dto.ToDoItemDto;
import com.todolist.app.ws.shared.Utils;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.shared.dto.UserDto;

@Service
public class ToDoListsServiceImpl implements ToDoListsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoListRepository todolistRepository;

	@Autowired
	Utils utils;

	@Override
	public List<ToDoListDto> getUserTodoLists(String userId) {
		List<ToDoListDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			return returnValue;

		Iterable<ToDoListEntity> todolists = todolistRepository.findAllByUserDetails(userEntity);
		for (ToDoListEntity todolistEntity : todolists) {
			returnValue.add(modelMapper.map(todolistEntity, ToDoListDto.class));
		}

		return returnValue;
	}

	@Override
	public ToDoListDto getToDoList(String todoListId) {
		ToDoListDto returnValue = null;

		ToDoListEntity todolistEntity = todolistRepository.findByTodoListId(todoListId);

		if (todolistEntity != null) {
			returnValue = new ModelMapper().map(todolistEntity, ToDoListDto.class);
		}

		return returnValue;
	}

	@Override
	public ToDoListDto createToDoList(ToDoListDto todolist, UserEntity userEntity) {

		for (int i = 0; i < todolist.getTodoitems().size(); i++) {
			ToDoItemDto todoItem = todolist.getTodoitems().get(i);
			todoItem.setTodoListDetail(todolist);
			todoItem.setTodoItemId(utils.generateTodoItemId(30));
			todolist.getTodoitems().set(i, todoItem);
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto todolistUser = modelMapper.map(userEntity, UserDto.class);

		todolist.setUserDetails(todolistUser);

		String publicToDoListId = utils.generateTodoListId(30);
		todolist.setTodoListId(publicToDoListId);

		ToDoListEntity todolistEntity = modelMapper.map(todolist, ToDoListEntity.class);

		ToDoListEntity storedToDoListDetails = todolistRepository.save(todolistEntity);

		ToDoListDto returnValue = modelMapper.map(storedToDoListDetails, ToDoListDto.class);
		;

		return returnValue;

	}

}
