package com.yedam.app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptVO;
import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired //필드주입 => 단순 테스트용
	private EmpMapper empMapper;
	@Autowired
	private DeptMapper deptMapper;
	
	
	//@Test
	void deptSelectTest() {
		List<DeptVO> list = deptMapper.selectDeptList();
		for(DeptVO dept : list) {
			
			System.out.println(dept);
		}
		assertTrue(!list.isEmpty());
	}
	
	//@Test
	void deptInfoTest() {
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentId(60);
		
		DeptVO findVO = deptMapper.selectDeptInfo(deptVO);
		
		assertEquals("IT", findVO.getDepartmentName());
		//첫번째 매개변수 : 기대하는 값
		//두번째 매개변수 : 실제 값
		// => 두 개가 같으면 테스트 성공, 다르면 실패
	}
	
	//@Test
	void deptInsertTest() throws ParseException {
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentName("BUILD");
		deptVO.setManagerId(100);
		deptVO.setLocationId(1700);
		
		int result = deptMapper.insertDeptInfo(deptVO);
		
		assertEquals(1, result);
		
	}
	
	@Test
	void deptUpdateTest() {
		//1) 단건 조회
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentId(280);
		
		DeptVO findVO = deptMapper.selectDeptInfo(deptVO);		
		
		//2) 수정할 데이터 
		findVO.setDepartmentName("Editor");
		findVO.setManagerId(null);
		
		
		//3) 수정
		int result = deptMapper.updateDeptInfo(findVO);
		assertEquals(1,result);
	}
	
	//@Test
	void deptdeleteTest() {
		int result = deptMapper.deleteDeptInfo(220);
		assertEquals(1,result);
	}
	
	//@Test
	void selectTest() {
		List<EmpVO> list = empMapper.selectEmpList();
		for(EmpVO emp : list) {
			
			System.out.println(emp);
		}
		assertTrue(!list.isEmpty());
	}
	
	
	//@Test
	void infoTest() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		
		assertEquals("King", findVO.getLastName());
		//첫번째 매개변수 : 기대하는 값
		//두번째 매개변수 : 실제 값
		// => 두 개가 같으면 테스트 성공, 다르면 실패
	}
	
	//@Test
	void insertTest() throws ParseException {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("kiki");
		empVO.setEmail("kii@daum.net");
		empVO.setJobId("IT_PROG");
		
		SimpleDateFormat sdf
			= new SimpleDateFormat("yyMMdd");
		Date date = sdf.parse("240501");
		
		empVO.setHireDate(date);
		
		int result = empMapper.insertEmpInfo(empVO);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void updateTest() {
		//1) 단건 조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(208);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);		
		
		//2) 수정할 데이터 
		findVO.setLastName("Park");
		findVO.setJobId(null);
		
		
		//3) 수정
		int result = empMapper.updateEmpInfo(findVO);
		assertEquals(1,result);
	}
	
	//@Test
	void deleteTest() {
		int result = empMapper.deleteEmpInfo(209);
		assertEquals(1,result);
	}
	
	//@Test
	void selectKeyTest() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("gana");
		empVO.setEmail("gg@naver.com");
		empVO.setJobId("IT_PROG");
		//employeeId : null
		int result = empMapper.insertEmpInfo(empVO);
		System.out.println("============" + empVO.getEmployeeId());
		assertEquals(1,result);
	}

}
