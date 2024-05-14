package com.example.TodoAPI.entity;

import java.time.LocalDateTime;

import com.example.TodoAPI.enums.Status;

import lombok.Data;

@Data
public class Todo {
	private int id;
	private String title;
	private Status status;
	private String detail;
	private LocalDateTime create_at;
	private LocalDateTime update_at;
	
}
