package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer>{
	Flux<Employee> findByOrganizationId(Integer organizationId);
}
