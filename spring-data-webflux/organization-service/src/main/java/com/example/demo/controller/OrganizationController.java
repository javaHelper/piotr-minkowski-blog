package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private WebClient.Builder webClient;

	@GetMapping
	public Flux<Organization> findAll(){
		return organizationRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Organization> findById(@PathVariable Integer id){
		return organizationRepository.findById(id);
	}

	@GetMapping("/{id}/withEmployees")
	public Mono<OrganizationDTO> findByIdWithEmployees(@PathVariable Integer id){
		Flux<Employee> employeeFlux = webClient.build()
				.get()
				.uri("http://localhost:8012/employees/organization/"+ id)
				.retrieve()
				.bodyToFlux(Employee.class);
		
		Mono<Organization> orgMono = organizationRepository.findById(id);
		
		return orgMono.zipWith(employeeFlux.collectList())
				.log()
				.map(tuple -> new OrganizationDTO(tuple.getT1().getId(),tuple.getT1().getName(), tuple.getT2()));
	}
}
