package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		//saveCustomers();
	}

	private void saveCustomers() {
		List<Customer> customers = List.of(
				Customer.builder().firstName("John").lastName("Doe").address("WA Street").build(),
				Customer.builder().firstName("Jane").lastName("Keer").address("AB Street").build(),
				Customer.builder().firstName("Victoria").lastName("Wixson").address("XY Street").build()
				);

		customerRepository.saveAll(customers);		
	}
}
