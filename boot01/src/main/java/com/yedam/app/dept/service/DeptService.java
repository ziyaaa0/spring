package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;


public interface DeptService {
	//사이트에서 제공하는 서비스
	//전체조회
	public List<DeptVO> findAllDept();
	//단건조회
	public DeptVO findDeptInfo(DeptVO deptVO);
	
	//등록
	public int createDeptInfo(DeptVO deptVO);
	
	//수정
	public Map<String, Object> modifyDeptInfo(DeptVO deptVO);
	
	//삭제
	public Map<String, Object> removeDeptInfo(int departmentId);
}
