package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.EmployeeClient;
import com.example.demo.entity.Department;
import com.example.demo.model.DepartmentModel;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentRepository repository;

	@Autowired
	private EmployeeClient employeeClient;

	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return null;
	}

	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		return repository.findById(id).orElseThrow();
	}

	@GetMapping("/")
	public List<Department> findAll() {
		LOGGER.info("Department find");
		return repository.findAll();
	}

	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return repository.findByOrganizationId(organizationId);
	}

	@GetMapping("/organization/{organizationId}/with-employees")
	public List<DepartmentModel> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);

		List<Department> departments = repository.findByOrganizationId(organizationId);

		//Inter-service communication using Feign client
		List<Employee> employees = employeeClient.findByDepartment(organizationId);


		return departments.stream()
				.map(e -> DepartmentModel.builder()
						.employees(employees)
						.id(e.getId())
						.name(e.getName())
						.organizationId(e.getOrganizationId())
						.build())
				.collect(Collectors.toList());
	}
}
