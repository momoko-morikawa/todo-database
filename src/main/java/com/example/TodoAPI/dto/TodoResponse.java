package com.example.TodoAPI.dto;

import java.time.LocalDateTime;

import com.example.TodoAPI.enums.Status;

import lombok.Data;

@Data
public class TodoResponse {
	private int id;
	private String title;
	private Status status;
	private String detail;
	private LocalDateTime create_at;
	private LocalDateTime update_at;
}
