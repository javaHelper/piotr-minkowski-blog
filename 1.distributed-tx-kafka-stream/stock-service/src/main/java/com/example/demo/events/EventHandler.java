package com.example.demo.events;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderManageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EventHandler {
	@Autowired
	private OrderManageService orderManageService;
	@Autowired
	private ProductRepository repository;

	@KafkaListener(id = "orders", topics = "orders", groupId = "stock")
	public void onEvent(Order o) {
		log.info("Received: {}", o);
		if (o.getStatus().equals("NEW"))
			orderManageService.reserve(o);
		else
			orderManageService.confirm(o);
	}

	@PostConstruct
	public void generateData() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int count = r.nextInt(1000);
			Product p = new Product(null, "Product" + i, count, 0);
			repository.save(p);
		}
	}
}
