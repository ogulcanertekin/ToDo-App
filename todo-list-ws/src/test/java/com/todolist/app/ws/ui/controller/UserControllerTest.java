package com.todolist.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.todolist.app.ws.service.impl.UserServiceImpl;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.shared.dto.UserDto;
import com.todolist.app.ws.ui.controller.UserController;
import com.todolist.app.ws.ui.model.response.UserRest;

class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	UserDto userDto;
	
	final String USER_ID = "bfhry47fhdjd7463gdh";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDto();
        userDto.setFirstName("Ogulcan");
        userDto.setLastName("Ertekin");
        userDto.setEmail("testuserservice@testing.com");
        userDto.setUserId(USER_ID);
        userDto.setTodoLists(getTodoListsDto());
        userDto.setEncryptedPassword("xcf58tugh47");
		
	}

	@Test
	final void testGetUser() {
	    when(userService.getUserByUserId(anyString())).thenReturn(userDto);	
	    
	    UserRest userRest = userController.getUser(USER_ID);
	    
	    assertNotNull(userRest);
	    assertEquals(USER_ID, userRest.getUserId());
	    assertEquals(userDto.getFirstName(), userRest.getFirstName());
	    assertEquals(userDto.getLastName(), userRest.getLastName());
	    assertTrue(userDto.getTodoLists().size() == userRest.getTodolists().size());
	}
	
	
	private List<ToDoListDto> getTodoListsDto() {
		ToDoListDto todolistDto = new ToDoListDto();
		todolistDto.setName("Friday To-Dos");

	    ToDoListDto newListDto = new ToDoListDto();
	    newListDto.setName("Saturday To-Dos");

	    List<ToDoListDto> todolists = new ArrayList<>();
		todolists.add(todolistDto);
		todolists.add(newListDto);
		
		return todolists;

	}

}
