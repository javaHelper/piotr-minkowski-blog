package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.FraudCheckResponse;

@FeignClient(name = "fraud-service")
public interface FraudClient {
	
	
	@GetMapping("/fraud/api/v1/fraud-check/{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}