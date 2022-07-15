package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Department;

@FeignClient(name = "department-service")
public interface DepartmentClient {

	@GetMapping("/department/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);

	@GetMapping("/department/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);

}