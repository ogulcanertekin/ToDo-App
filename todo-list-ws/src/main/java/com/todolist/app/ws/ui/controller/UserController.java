package com.todolist.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.app.ws.exceptions.UserServiceException;
import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.io.entity.UserEntity;
import com.todolist.app.ws.io.repositories.TodoListRepository;
import com.todolist.app.ws.io.repositories.UserRepository;
import com.todolist.app.ws.service.ToDoItemService;
import com.todolist.app.ws.service.ToDoListsService;
import com.todolist.app.ws.service.UserService;
import com.todolist.app.ws.shared.dto.ToDoItemDto;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.shared.dto.UserDto;
import com.todolist.app.ws.ui.model.request.ToDoItemRequestModel;
import com.todolist.app.ws.ui.model.request.ToDoItemStatusRequestModel;
import com.todolist.app.ws.ui.model.request.ToDoListRequestModel;
import com.todolist.app.ws.ui.model.request.UserDetailsRequestModel;
import com.todolist.app.ws.ui.model.response.ErrorMessages;
import com.todolist.app.ws.ui.model.response.ToDoItemRest;
import com.todolist.app.ws.ui.model.response.ToDoListsRest;
import com.todolist.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ToDoListsService todolistsService;

	@Autowired
	ToDoItemService todoitemService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoListRepository todolistRepository;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserRest returnValue = new UserRest();

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		List<ToDoListRequestModel> todolists = new ArrayList<>();
		userDetails.setTodolists(todolists);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);

		return returnValue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		List<UserRest> returnValue = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit);

		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}

		return returnValue;
	}

	@GetMapping(path = "/{id}/todolists", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<ToDoListsRest> GetUserToDoLists(@PathVariable String id) {
		List<ToDoListsRest> returnValue = new ArrayList<>();

		List<ToDoListDto> todolistsDto = todolistsService.getUserTodoLists(id);
		if (todolistsDto != null && !todolistsDto.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ToDoListsRest>>() {
			}.getType();
			returnValue = new ModelMapper().map(todolistsDto, listType);
		}

		return returnValue;
	}

	@GetMapping(path = "/{userId}/todolists/{todolistId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, "application/hal+json" })
	public ToDoListsRest getUserToDoList(@PathVariable String todolistId) {

		ToDoListDto todolistsDto = todolistsService.getToDoList(todolistId);

		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(todolistsDto, ToDoListsRest.class);
	}

	@PostMapping(path = "/{id}/todolists", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ToDoListsRest createToDoList(@RequestBody ToDoListRequestModel todoListDetails, @PathVariable String id)
			throws Exception {
		ToDoListsRest returnValue = new ToDoListsRest();

		if (todoListDetails.getName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		ToDoListDto todolistDto = modelMapper.map(todoListDetails, ToDoListDto.class);

		List<ToDoItemDto> todoitems = new ArrayList<>();
		todolistDto.setTodoitems(todoitems);

		UserEntity userEntity = userRepository.findByUserId(id);

		ToDoListDto createdTodoList = todolistsService.createToDoList(todolistDto, userEntity);
		returnValue = modelMapper.map(createdTodoList, ToDoListsRest.class);

		return returnValue;
	}

	@PostMapping(path = "/todolists/{id}/todoitems", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ToDoItemRest createToDoItem(@RequestBody ToDoItemRequestModel todoItemDetails, @PathVariable String id)
			throws Exception {
		ToDoItemRest returnValue = new ToDoItemRest();

		if (todoItemDetails.getName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		ToDoItemDto todoitemDto = modelMapper.map(todoItemDetails, ToDoItemDto.class);

		ToDoListEntity todolistEntity = todolistRepository.findByTodoListId(id);

		ToDoItemDto createdTodoItem = todoitemService.createToDoItem(todoitemDto, todolistEntity);
		returnValue = modelMapper.map(createdTodoItem, ToDoItemRest.class);

		returnValue.setTodoListId(id);

		return returnValue;
	}

	@GetMapping(path = "/todolists/{id}/todoitems", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<ToDoListsRest> GetToDoItemsOfList(@PathVariable String id) {
		List<ToDoListsRest> returnValue = new ArrayList<>();

		List<ToDoItemDto> todoitemsDto = todoitemService.getToDoItemsOfList(id);
		if (todoitemsDto != null && !todoitemsDto.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ToDoItemRest>>() {
			}.getType();
			returnValue = new ModelMapper().map(todoitemsDto, listType);
		}

		return returnValue;
	}

	@PutMapping(path = "/todoitems/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ToDoItemRest EditToDoItemStatus(@RequestBody ToDoItemStatusRequestModel todoitemStatus,
			@PathVariable String id) throws Exception {
		ToDoItemDto todoitem = todoitemService.updateToDoItemStatus(todoitemStatus);

		ModelMapper modelMapper = new ModelMapper();
		ToDoItemRest updatedValue = modelMapper.map(todoitem, ToDoItemRest.class);

		return updatedValue;
	}
}
