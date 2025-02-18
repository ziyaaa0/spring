package com.yedam.app.common.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	//Content-type : application/x-www-form-urlencoded
	//QueryString(질의문자열) : key=value&key=value&.
	//Method: 상관없음
	
	//QueryString => 커맨드 객체(어노테이션 x) : 객체
	@RequestMapping(path="comobj",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	//QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path = "reqparm",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
	(@RequestParam Integer employeeId, //필수
					String lastName,	//생략가능
	 @RequestParam(name="message",
	 			   defaultValue="No message",
					required = true) String msg) {
		String result = "";
		result += "path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;			
		}
	
	// @PathVariable : 경로에 값을  포함하는 방식, 여러개 사용 가능 "path/{id}//{name}"
	// Content-type도 상관없음
	// Method도 상관없음
	@RequestMapping("path/{id}")
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	// Content-type : application/json
	// JSON 포맷 => @requestBody 배열 or 객체
	// Method : POST, PUT
	// @requestBody 는 Single Object만 처리가능
	// JSON => 객체
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	// JSON => 배열
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
}
