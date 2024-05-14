package com.example.TodoAPI.dto;

import lombok.Data;

@Data
public class TodoRequest {
	private String title;
	private int status;
	private String detail;
}
