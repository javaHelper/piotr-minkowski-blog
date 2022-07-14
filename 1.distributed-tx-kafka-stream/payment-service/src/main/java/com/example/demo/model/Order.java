package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
	private Long id;
	private Long customerId;
	private Long productId;
	private int productCount;
	private int price;
	private String status;
	private String source;
}