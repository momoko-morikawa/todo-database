package com.example.TodoAPI.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.TodoAPI.entity.Todo;
import com.example.TodoAPI.enums.Status;

@Mapper
public interface TodoMapper {
	
	void addTodo(@Param("todo") Todo todo); //todoの追加
	List<Todo> showAll(); //全部のtodoを表示
	Todo findById(int id); //Todo一件表示
	int deleteTodo(int id);
	int updateTodo(int id, @Param("todo") Todo todo);
	List<Todo> filteredByTitle(String title);
	List<Todo> filteredByStatus(Status status);
	List<Todo> sortByStatus();
}
