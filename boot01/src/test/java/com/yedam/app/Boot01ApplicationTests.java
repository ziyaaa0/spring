package com.yedam.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired //필드주입 => 단순 테스트용
	private EmpMapper empMapper;
	
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
	
	@Test
	void insertTest() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Kang");
		empVO.setEmail("kang@daum.net");
		empVO.setJobId("SA_REP");
		
		int result = empMapper.insertEmpInfo(empVO);
		
		assertEquals(1, result);
		
	}

}
