package com.emp.main.service;

import java.util.List;

import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;

public interface EmployeeService {
	
	public List<FetchEmpDeptRes> fetchEmployeeAndDepartmentDetails(FetchEmployeeReq fetchEmployeeReq);
}
