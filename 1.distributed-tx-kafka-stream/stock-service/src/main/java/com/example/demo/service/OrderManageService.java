package com.example.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderManageService {

	private static final String SOURCE = "stock";
	private ProductRepository repository;
	private KafkaTemplate<Long, Order> template;

	public OrderManageService(ProductRepository repository, KafkaTemplate<Long, Order> template) {
		this.repository = repository;
		this.template = template;
	}

	public void reserve(Order order) {
		Product product = repository.findById(order.getProductId()).orElseThrow();
		log.info("Found: {}", product);
		
		if (order.getStatus().equals("NEW")) {
			if (order.getProductCount() < product.getAvailableItems()) {
				product.setReservedItems(product.getReservedItems() + order.getProductCount());
				product.setAvailableItems(product.getAvailableItems() - order.getProductCount());
				order.setStatus("ACCEPT");
				repository.save(product);
			} else {
				order.setStatus("REJECT");
			}
			
			// Raise an alert to stock-orders
			template.send("stock-orders", order.getId(), order);
			log.info("Sent: {}", order);
		}
	}

	public void confirm(Order order) {
		Product product = repository.findById(order.getProductId()).orElseThrow();
		log.info("Found: {}", product);
		
		if (order.getStatus().equals("CONFIRMED")) {
			product.setReservedItems(product.getReservedItems() - order.getProductCount());
			repository.save(product);
		} else if (order.getStatus().equals("ROLLBACK") && !order.getSource().equals(SOURCE)) {
			product.setReservedItems(product.getReservedItems() - order.getProductCount());
			product.setAvailableItems(product.getAvailableItems() + order.getProductCount());
			repository.save(product);
		}
	}

}