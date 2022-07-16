package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRegistrationRequest;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@Autowired
	private Environment env;
	
	@PostMapping
    public void register(@RequestBody CustomerRegistrationRequest customer) throws JsonProcessingException {
        log.info("register {}", customer);
        
        service.register(customer);
    }
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port") + ", with Key = " + env.getProperty("app.test.key");
	}
}
