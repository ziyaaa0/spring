package com.yedam.app.security.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.security.service.LoginUserVO;
import com.yedam.app.security.service.UserVO;

@Controller
public class SampleController {
	// void : 경로를 기반으로 파일을 생성한 경우 사용
	// classpath:/templates/ + URI + .html
	@GetMapping("all")
	public void all() {}
	
	@GetMapping("user")
	public void user(@AuthenticationPrincipal UserDetails loginVO) {
		UserVO userInfo = ((LoginUserVO) loginVO).getUserVO();
		System.out.println(userInfo);
	}
	@GetMapping("admin")
	public void admin() {}

}
