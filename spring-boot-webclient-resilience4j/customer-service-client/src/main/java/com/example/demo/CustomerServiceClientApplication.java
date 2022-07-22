package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.demo.config.CircuitBreakerConfiguration;
import com.example.demo.config.WebClientConfiguration;

@SpringBootApplication
@Import({WebClientConfiguration.class, CircuitBreakerConfiguration.class})
public class CustomerServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceClientApplication.class, args);
	}

}
