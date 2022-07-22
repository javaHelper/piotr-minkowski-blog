package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.CustomerVO;
import com.example.demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers",
produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {


	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerVO> createCustomer(@Valid @RequestBody CustomerVO customerVO, UriComponentsBuilder uriBuilder) throws Exception {
		CustomerVO newCustomerVO = customerService.saveCustomer(customerVO);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCustomerVO);
	}

	@GetMapping
	public ResponseEntity<List<CustomerVO>> getCustomers() {
		List<CustomerVO> customersVos = customerService.getCustomers();
		return ResponseEntity.ok().body(customersVos);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerVO> getCustomer(@PathVariable Integer customerId) {
		CustomerVO customerVO = customerService.getCustomer(customerId);
		return ResponseEntity.ok().body(customerVO);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerVO customerVO) throws Exception {
		customerService.updateCustomer(customerId, customerVO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) throws Exception {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}
}