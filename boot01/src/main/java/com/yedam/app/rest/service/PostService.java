package com.yedam.app.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {
	private final String origin
			= "https://jsonplaceholder.typicode.com";
	
	//단건조회
	
	public void findOneById(Integer id) {
		String selectedService = "/posts/" + id;
		String url = origin + selectedService;
		
		RestTemplate restTemplate = new RestTemplate();
		Object obj = restTemplate.getForObject(url, Post.class);
		System.out.println(obj);
	}
}
