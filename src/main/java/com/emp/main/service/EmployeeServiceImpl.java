package com.emp.main.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emp.main.bean.FetchEmpDeptRes;
import com.emp.main.bean.FetchEmployeeReq;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	LoadBalancerClient loadBalancer;
	
	@Value("${employee.portal.url}")
	private String employeePortalUrl;
	
	public List<FetchEmpDeptRes> fetchEmployeeAndDepartmentDetails(FetchEmployeeReq fetchEmployeeReq) {

		ServiceInstance serviceInstance = loadBalancer.choose(employeePortalUrl);
		if(serviceInstance != null) {
			String url = serviceInstance.getUri().toString() + "/api/fetchEmployeeWithDepartmentDetails";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<FetchEmployeeReq> requestEntity = new HttpEntity<>(fetchEmployeeReq, headers);
			
			ResponseEntity<List<FetchEmpDeptRes>> responseEntity = new RestTemplate()
					.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<FetchEmpDeptRes>>() {});
			if(responseEntity.getStatusCode() == HttpStatus.OK) {
				return responseEntity.getBody();
			}
		}
		return Collections.emptyList();
	}
}
