package com.emp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;
import com.emp.main.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/fetchEmpAndDeptDetails")
	public ResponseEntity<List<FetchEmpDeptRes>> fetchEmployeeAndDepartmentDetails(@RequestBody FetchEmployeeReq fetchEmployeeReq) {
		return new ResponseEntity<List<FetchEmpDeptRes>>(employeeService.fetchEmployeeAndDepartmentDetails(fetchEmployeeReq), HttpStatus.OK);
	}
}
