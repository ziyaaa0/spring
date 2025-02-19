package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller //route : 사용자의 요청(endpoint)와 그에 대한 처리
			// : URL + METHOD => Service => View
public class EmpController {
	//해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;
	
	@Autowired
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	//GET : 조회, 빈페이지
	//POST : 데이터 조작(등록, 수정, 삭제)
	
	//전체조회 : GET controller1개
	//1) URL + METHOD
	@GetMapping("empList") //Model = Request + Response
	public String empList(Model model) {
		//2) Service
		List<EmpVO> list = empService.findAllEmp();
		
		//2-1)Service의 결과를 View에 전달
		model.addAttribute("emps",list);
		//3) View
		return "emp/list";	
		//prefix : classpath:/templates/
		//suffix : .html
		
		//prifix + return + suffix : 경로
		// classpath:/templates/emp/list.html
	}
	
	//단건조회 : GET + 전달받을 데이터 => QueryString :  controller1개
	//1) URL + METHOD
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		//2) Service
		 EmpVO findVO = empService.findEmpInfo(empVO);
		//2-1)Service의 결과를 View에 전달
		 model.addAttribute("emp", findVO);		
		//3) View
		 return "emp/info";
		 
		// classpath:/templates/emp/info.html
	}
	
	
	//등록 - 등록 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}// classpath:/templates/emp/insert.html
	
	//등록 - 처리 : POST => form 태그를 통한 submit
	//				=> 이미지가 있는 경우 multipart/form-data 
	//				=> 없는경우 application/x-www-form-urlencoded
	
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createEmpInfo(empVO);
		String url = null;
		if(eid > -1) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId=" + eid;
		}else {
			//등록되지 않은 경우
			url = "redirect:empList";
		} 
		return url;		
	}
	
	
	//수정 - 페이지 : GET <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdate(EmpVO empVO, Model model) {
		//2) Service
		 EmpVO findVO = empService.findEmpInfo(empVO);
		//2-1)Service의 결과를 View에 전달
		 model.addAttribute("emp", findVO);		
		//3) View
		 return "emp/update";
	}	// classpath:/templates/emp/update.html
	
	//수정 - 처리 : POST / AJAX => JSON (@RequestBody)
	@PostMapping("empUpdate")
	@ResponseBody // AJAX
	public Map<String, Object>
		empUpdateAJAXJSON(@RequestBody EmpVO empVO){
		return empService.modifyEmpInfo(empVO);
	}
	
	
	//단건삭제 - 처리 : GET + 전달받을 데이터 1건
			//		=> QueryString(@RequestParam)
	@GetMapping("empDelete") // /empDelete?employeeId=100
	public String empDelete(Integer employeeId) {
		
		empService.removeEmpInfo(employeeId);
		return "redirect:empList";
	}
}
