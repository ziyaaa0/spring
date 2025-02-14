package com.yedam.app.mybatis.service;

import java.util.Date;

import lombok.Data;

//값을 담는 개체 : VO 클래스
@Data
public class EmpInfo {

	private int employeeId;
	private String lastName;
	private Date hireDate;
	private double salary;
	
}
