package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public Flux<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Employee> findById(@PathVariable("id") Integer id) {
		return employeeRepository.findById(id);
	}

	@GetMapping("/organization/{organizationId}")
	public Flux<Employee> findByorganizationId(@PathVariable Integer organizationId) {
		return employeeRepository.findByOrganizationId(organizationId);
	}

	@PostMapping
	public Mono<Employee> createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
}
