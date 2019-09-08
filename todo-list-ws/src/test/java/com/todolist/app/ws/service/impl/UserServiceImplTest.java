package com.todolist.app.ws.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.todolist.app.ws.exceptions.UserServiceException;
import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.io.entity.UserEntity;
import com.todolist.app.ws.io.repositories.UserRepository;
import com.todolist.app.ws.shared.Utils;
import com.todolist.app.ws.shared.dto.ToDoListDto;
import com.todolist.app.ws.shared.dto.UserDto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.todolist.app.ws.io.repositories.UserRepository;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
 
	@Mock
	Utils utils;
	

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
 
	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";
	
	UserEntity userEntity;
 
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Ogulcan");
		userEntity.setLastName("Ertekin");
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setEmail("testuserservice@testing.com");
		userEntity.setTodoLists(getToDoListsEntity());
	}

	@Test
	final void testGetUser() {
 
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("testuserservice@testing.com");

		assertNotNull(userDto);
		assertEquals("Ogulcan", userDto.getFirstName());

	}

	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userService.getUser("testuserservice@testing.com");
				}

		);
	}
	
	@Test
	final void testCreateUser_CreateUserServiceException()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setTodoLists(getTodoListsDto());
		userDto.setFirstName("Ogulcan");
		userDto.setLastName("Ertekin");
		userDto.setPassword("12345678");
		userDto.setEmail("testuserservice@testing.com");
 	
		assertThrows(UserServiceException.class,

				() -> {
					userService.createUser(userDto);
				}

		);
	}
	
	@Test
	final void testCreateUser()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generateTodoListId(anyInt())).thenReturn("hgfnghtyrir884");
		when(utils.generateUserId(anyInt())).thenReturn(userId);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
 		
		UserDto userDto = new UserDto();
		userDto.setTodoLists(getTodoListsDto());
		userDto.setFirstName("Ogulcan");
		userDto.setLastName("Ertekin");
		userDto.setPassword("12345678");
		userDto.setEmail("testuserservice@testing.com");

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		assertEquals(storedUserDetails.getTodoLists().size(), userEntity.getTodoLists().size());
		verify(utils,times(storedUserDetails.getTodoLists().size())).generateTodoListId(30);
		verify(bCryptPasswordEncoder, times(1)).encode("12345678");
		verify(userRepository,times(1)).save(any(UserEntity.class));
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
	
	private List<ToDoListEntity> getToDoListsEntity()
	{
		List<ToDoListDto> todolists = getTodoListsDto();
		
	    Type listType = new TypeToken<List<ToDoListEntity>>() {}.getType();
	    
	    return new ModelMapper().map(todolists, listType);
	}
	
}


