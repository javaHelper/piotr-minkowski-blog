package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Order;

@Service
public class OrderManageService {

	private static final String STOCK = "STOCK";
	private static final String PAYMENT = "PAYMENT";
	private static final String ROLLBACK = "ROLLBACK";
	private static final String REJECT = "REJECT";
	private static final String REJECTED = "REJECTED";
	private static final String CONFIRMED = "CONFIRMED";
	private static final String ACCEPT = "ACCEPT";

	public Order confirm(Order orderPayment, Order orderStock) {
		Order o = Order.builder().id(orderPayment.getId())
				.customerId(orderPayment.getCustomerId())
				.productId(orderPayment.getProductId())
				.productCount(orderPayment.getProductCount())
				.price(orderPayment.getPrice())
				.build();
		

		if (orderPayment.getStatus().equals(ACCEPT) && orderStock.getStatus().equals(ACCEPT)) {
			o.setStatus(CONFIRMED);
		} else if (orderPayment.getStatus().equals(REJECT) && orderStock.getStatus().equals(REJECT)) {
			o.setStatus(REJECTED);
		} else if (orderPayment.getStatus().equals(REJECT) || orderStock.getStatus().equals(REJECT)) {
			String source = orderPayment.getStatus().equals(REJECT) ? PAYMENT : STOCK;
			o.setStatus(ROLLBACK);
			o.setSource(source);
		}
		return o;
	}
}