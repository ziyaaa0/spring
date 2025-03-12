package com.yedam.app.rest.service;

import lombok.Data;

@Data
public class Post {
	private Integer userId;
	private Integer id;
	private String title;
	private String body;
	
}
