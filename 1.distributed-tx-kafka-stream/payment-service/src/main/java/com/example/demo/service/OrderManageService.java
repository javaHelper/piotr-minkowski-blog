package com.example.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderManageService {

	private static final String SOURCE = "payment";
	private CustomerRepository repository;
	private KafkaTemplate<Long, Order> template;

	public OrderManageService(CustomerRepository repository, KafkaTemplate<Long, Order> template) {
		this.repository = repository;
		this.template = template;
	}

	public void reserve(Order order) {
		Customer customer = repository.findById(order.getCustomerId()).orElseThrow();
		log.info("#### Found: {}", customer);
		
		
		if (order.getPrice() < customer.getAmountAvailable()) {
			order.setStatus("ACCEPT");
			customer.setAmountReserved(customer.getAmountReserved() + order.getPrice());
			customer.setAmountAvailable(customer.getAmountAvailable() - order.getPrice());
		} else {
			order.setStatus("REJECT");
		}
		
		order.setSource(SOURCE);
		repository.save(customer);
		
		
		// Raise event for Payment-orders
		template.send("payment-orders", order.getId(), order);
		
		log.info("Sent: {}", order);
	}

	public void confirm(Order order) {
		Customer customer = repository.findById(order.getCustomerId()).orElseThrow();
		log.info("Found: {}", customer);
		
		
		if (order.getStatus().equals("CONFIRMED")) {
			customer.setAmountReserved(customer.getAmountReserved() - order.getPrice());
			repository.save(customer);
		} else if (order.getStatus().equals("ROLLBACK") && !order.getSource().equals(SOURCE)) {
			customer.setAmountReserved(customer.getAmountReserved() - order.getPrice());
			customer.setAmountAvailable(customer.getAmountAvailable() + order.getPrice());
			repository.save(customer);
		}

	}
}