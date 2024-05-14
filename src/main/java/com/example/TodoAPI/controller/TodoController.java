package com.example.TodoAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.TodoAPI.dto.TodoRequest;
import com.example.TodoAPI.dto.TodoResponse;
import com.example.TodoAPI.entity.Todo;
import com.example.TodoAPI.enums.Status;
import com.example.TodoAPI.mapper.TodoMapper;

@RestController
@RequestMapping("todos")
public class TodoController {
	
	//Mapperを使えるようにする
	@Autowired
	TodoMapper todoMapper;
	
	//クライアントが入力した数値をEnumに変更するメソッド
	private Status convertToStatus(int statusValue) {
		switch(statusValue) {
		case 1:
			return Status.未着手;
		case 2:
			return Status.進行中;
		case 3:
			return Status.完了;
		default:
			throw new IllegalArgumentException("存在しないステータス番号：" + statusValue);
		}
	}
	//todo追加
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addTodo(@RequestBody TodoRequest todoRequest) {
		Todo todo = new Todo();
		BeanUtils.copyProperties(todoRequest, todo);
		
		todo.setStatus(convertToStatus(todoRequest.getStatus())); //ここで数値をenumに変換
		todoMapper.addTodo(todo); //型をtodoに揃えれたので設定
		
	}
	
	//todo全表示
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public List<TodoResponse> showAll(){
		List<TodoResponse> todoResponseList = new ArrayList<>();
		List<Todo> todoList = todoMapper.showAll();
		
		todoList.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);
			todoResponseList.add(todoResponse);
		});
		
		return todoResponseList;
	}
	
	//todo一件表示
	@GetMapping("/{id}")
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TodoResponse> findById(@PathVariable int id) {
		Todo todo = todoMapper.findById(id);
		if(todo == null) {
			return ResponseEntity.notFound().build();
		}
		
		TodoResponse todoResponse = new TodoResponse();
		BeanUtils.copyProperties(todo, todoResponse);
		
		return ResponseEntity.ok(todoResponse);
	}
	
	//todo削除
	@DeleteMapping("/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteTodo(@PathVariable int id) {
		
		int deleteRows = todoMapper.deleteTodo(id);
		if(deleteRows == 0) {
			return ResponseEntity.notFound().build();
		}
		
		todoMapper.deleteTodo(id);
		return ResponseEntity.noContent().build();
	}
	
	//todoの更新
	@PutMapping("/{id}")
	public ResponseEntity<TodoResponse> updateTodo(@PathVariable int id, @RequestBody TodoRequest todoRequest){
		Todo todo = new Todo();
		BeanUtils.copyProperties(todoRequest, todo);
		
		todo.setStatus(convertToStatus(todoRequest.getStatus()));
		
		int affectedRows = todoMapper.updateTodo(id, todo);
		if(affectedRows == 0) {
			return ResponseEntity.notFound().build();
		}
		
		
		//ここからは更新後のものを取得して返す処理
		Todo updatedTodo = todoMapper.findById(id);
		
		TodoResponse todoResponse = new TodoResponse();
		BeanUtils.copyProperties(updatedTodo, todoResponse);
		
		
		return ResponseEntity.ok(todoResponse);
	}
	
	//タイトルで絞り込み
	@GetMapping("/title/{title}")
	public List<TodoResponse> filteredByTitle(@PathVariable String title){
		List<TodoResponse> todoResponseList = new ArrayList<>();
		List<Todo> todoList = todoMapper.filteredByTitle(title);
		
		todoList.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);
			todoResponseList.add(todoResponse);
		});
		return todoResponseList;
			
	}
	
	//ステータスで絞り込み
	@GetMapping("/status/{status}")
	public List<TodoResponse> filteredByStatus(@PathVariable("status") int statusValue){
		Status status = convertToStatus(statusValue);
		List<TodoResponse> todoResponseList = new ArrayList<>();
		List<Todo> todoList = todoMapper.filteredByStatus(status);
		
		todoList.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);
			todoResponseList.add(todoResponse);
		});
		return todoResponseList;
	}
	
	//ステータスで並び替え
	@GetMapping("/sort")
	public List<TodoResponse> sortByStatus(){
		List<TodoResponse> todoResponseList = new ArrayList<>();
		List<Todo> todoList = todoMapper.sortByStatus();
		
		todoList.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);
			todoResponseList.add(todoResponse);
		});
		return todoResponseList;
	} 
}