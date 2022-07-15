package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.DepartmentClient;
import com.example.demo.client.EmployeeClient;
import com.example.demo.entity.Organization;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.OrganizationModel;
import com.example.demo.repository.OrganizationRepository;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	OrganizationRepository repository;
	@Autowired
	DepartmentClient departmentClient;
	@Autowired
	EmployeeClient employeeClient;

	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		//return repository.add(organization);
		return null;
	}

	@GetMapping
	public List<Organization> findAll() {
		LOGGER.info("Organization find");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		return repository.findById(id).orElseThrow();
	}

	@GetMapping("/{id}/with-departments")
	public OrganizationModel findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);

		// Inter-service communication using Feign
		List<Department> departments = departmentClient.findByOrganization(id);

		Organization organization = repository.findById(id).orElseThrow();

		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setAddress(organization.getAddress());
		organizationModel.setId(organization.getId());
		organizationModel.setName(organization.getName());
		organizationModel.setDepartments(departments);
		return organizationModel;
	}

	@GetMapping("/{id}/with-departments-and-employees")
	public OrganizationModel findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);

		// Inter-service communication using Feign
		List<Department> departments = departmentClient.findByOrganizationWithEmployees(id);


		Organization organization = repository.findById(id).orElseThrow();

		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setAddress(organization.getAddress());
		organizationModel.setId(organization.getId());
		organizationModel.setName(organization.getName());
		organizationModel.setDepartments(departments);
		return organizationModel;
	}

	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization organization = repository.findById(id).orElseThrow();
		
		// Inter-service communication using Feign
		List<Employee> employees = employeeClient.findByOrganization(organization.getId());
		
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setId(organization.getId());
		organizationModel.setName(organization.getName());
		organizationModel.setAddress(organization.getAddress());
		organizationModel.setEmployees(employees);
		return organization;
	}
}