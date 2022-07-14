package com.example.demo.event;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.OrderManageService;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;


@Slf4j
@Configuration
public class EventHandler {
	@Autowired
	OrderManageService orderManageService;

	@Autowired
	private CustomerRepository repository;

	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
	public void onEvent(Order order) {
		log.info("## Received: {}", order);
		
		if (order.getStatus().equals("NEW"))
			orderManageService.reserve(order);
		else
			orderManageService.confirm(order);
	}

	@PostConstruct
	public void generateData() {
		Random r = new Random();
		Faker faker = new Faker();
		
		// Create 100 customers and save it.
		for (int i = 0; i < 100; i++) {
			int count = r.nextInt(1000);
			Customer c = new Customer(null, faker.name().fullName(), count, 0);
			repository.save(c);
		}
	}
}
