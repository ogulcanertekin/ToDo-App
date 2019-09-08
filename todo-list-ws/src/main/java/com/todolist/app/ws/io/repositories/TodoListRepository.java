package com.todolist.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todolist.app.ws.io.entity.ToDoListEntity;
import com.todolist.app.ws.io.entity.UserEntity;

@Repository
public interface TodoListRepository extends CrudRepository<ToDoListEntity, Long> {

	List<ToDoListEntity> findAllByUserDetails(UserEntity userEntity);

	ToDoListEntity findByTodoListId(String todolistId);
}
