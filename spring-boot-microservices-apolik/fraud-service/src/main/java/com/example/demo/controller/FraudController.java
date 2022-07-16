package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FraudCheckResponse;
import com.example.demo.service.FraudCheckService;

@RestController
@RequestMapping("/fraud/api/v1/fraud-check")
public class FraudController {

	@Autowired
	private FraudCheckService service;

	@Autowired
	private Environment env;
	
	@GetMapping("/{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
		Boolean fraudulent = service.isFraudulentCustomer(customerId);
		return new FraudCheckResponse(fraudulent);
	}
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port") + ", with Key = " + env.getProperty("app.test.key");
	}
}