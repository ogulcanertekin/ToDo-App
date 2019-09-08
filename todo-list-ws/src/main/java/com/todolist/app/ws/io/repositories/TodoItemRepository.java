package com.todolist.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.todolist.app.ws.io.entity.ToDoItemEntity;
import com.todolist.app.ws.io.entity.ToDoListEntity;

public interface TodoItemRepository extends CrudRepository<ToDoItemEntity, Long> {

	List<ToDoItemEntity> findAllBytoDoListDetails(ToDoListEntity todolistEntity);

	ToDoItemEntity findByTodoItemId(String todoitemId);

	ToDoItemEntity findByName(String todoitemName);
}
