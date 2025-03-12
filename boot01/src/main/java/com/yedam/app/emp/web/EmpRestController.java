package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

//REST API 1)AJAX 2) JSON 3) URI 규칙
@CrossOrigin //origins="http://127.0.0.1:5500/") 지정된 경로에만 가능
@RestController //모든 메서드에 @ResponseBody가 적용됨
//Controller = URI+METHOD -> Service -> Response(View or Data)
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService empService;
	
	// 전체조회
	@GetMapping("/emps")
	public List<EmpVO> empList(){
		return empService.findAllEmp();
	}
	// 단건조회
	@GetMapping("/emps/{eid}")
	public EmpVO empInfo(@PathVariable Integer eid) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(eid);
		return empService.findEmpInfo(empVO);
	}
	
	// 등록
	@PostMapping("/emps")
	public EmpVO empInsert(@RequestBody EmpVO empVO) {
		int result = empService.createEmpInfo(empVO);
		
		if(result > -1) {
			return empVO;
		}else {
			return new EmpVO();
		}
		
	}
	
	// 수정
	@PutMapping("/emps/{eid}")
	public Map<String, Object> empUpdate(@PathVariable Integer eid, @RequestBody EmpVO empVO){
		empVO.setEmployeeId(eid);
		return empService.modifyEmpInfo(empVO);
	}
	
	// 삭제
	@DeleteMapping("/emps/{eid}")
	public Map<String,Object> empDelete(@PathVariable Integer eid) {
		return empService.removeEmpInfo(eid);
	}
}
