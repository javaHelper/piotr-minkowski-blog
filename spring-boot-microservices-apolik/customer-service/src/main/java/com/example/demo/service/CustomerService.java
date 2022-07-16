package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.FraudClient;
import com.example.demo.entity.Customer;
import com.example.demo.kafkaevent.NotificationProducer;
import com.example.demo.model.CustomerRegistrationRequest;
import com.example.demo.model.FraudCheckResponse;
import com.example.demo.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private FraudClient fraudClient;
	
	@Autowired
	private NotificationProducer notificationProducer;
	

	public void register(CustomerRegistrationRequest request) throws JsonProcessingException {
		log.info("register {}", request);

		Customer customer = Customer.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.build();

		Customer c = repository.findByEmail(request.getEmail());
		if(c != null) {
			//throw new IllegalStateException("Customer already exists!!");
		}else {
			c = repository.save(customer);
		}

		// Inter-service communication using Feign Client
		FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(c.getId());

		if (fraudCheckResponse.getFraudster()) {
			throw new IllegalStateException("Fraud has been operated on this customer !!");
		}

		// send notification to consumer
		notificationProducer.sendNotification(c);
	}
}
