package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aop.service.AaaService;
import com.yedam.app.rest.service.PostService;

@SpringBootTest
public class ServicesTests {
	
	@Autowired
	private AaaService aaaService;
	
	//@Test
	public void Transcational() {
		aaaService.aaaInsert();
	}
	
	@Autowired
	private PostService postService;
	
	@Test
	public void RestTemplate() {
		postService.findOneById(1);
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncoder() {
		String password ="1234";
		
		String enPwd = passwordEncoder.encode(password);
		System.out.println("enPwd : " + enPwd);
		
		boolean matchResult = passwordEncoder.matches(password, enPwd);
		System.out.println("matchesResult : " + matchResult);
	}
}
