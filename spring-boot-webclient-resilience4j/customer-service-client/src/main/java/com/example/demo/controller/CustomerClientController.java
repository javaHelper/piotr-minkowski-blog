package com.example.demo.controller;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.CustomerVO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerClientController {
	private static final String CIRCUIT_BREAKER_NAME = "customer-service";
	
	private final WebClient webClient;
	private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

	@PostMapping("/customers")
	public Mono<CustomerVO> createCustomer(@RequestBody CustomerVO customerVO){
		return this.webClient
				.post()
				.uri("/customers")
				.body(Mono.just(customerVO), CustomerVO.class)
				.retrieve()
				.bodyToMono(CustomerVO.class)
				.transform(it -> {
					ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(CIRCUIT_BREAKER_NAME);
					return rcb.run(it, throwable -> Mono.just(CustomerVO.builder().build()));
				});
	}


	@GetMapping("/customers/{customerId}")
	public Mono<CustomerVO> getCustomer(@PathVariable String customerId){
		return this.webClient
				.get()
				.uri("/customers/" + customerId)
				.retrieve()
				.bodyToMono(CustomerVO.class)
				.transform(it -> {
					ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(CIRCUIT_BREAKER_NAME);
					return rcb.run(it, throwable -> Mono.just(CustomerVO.builder().build()));
				});
	}

	@PutMapping("/customers/{customerId}")
	public Mono<CustomerVO> updateCustomer(@PathVariable String customerId, @RequestBody CustomerVO customerVO){
		return this.webClient
				.put()
				.uri("/customers/" + customerVO.getCustomerId())
				.body(Mono.just(customerVO), CustomerVO.class)
				.retrieve()
				.bodyToMono(CustomerVO.class)
				.transform(it -> {
					ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(CIRCUIT_BREAKER_NAME);
					return rcb.run(it, throwable -> Mono.just(CustomerVO.builder().build()));
				});
	}
	
	
	@DeleteMapping("/customers/{customerId}")
    public Mono<String> deleteCustomer(@PathVariable String customerId){
        return webClient.delete()
                .uri("/customers/" + customerId)
                .retrieve()
                .bodyToMono(String.class)
                .transform(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create(CIRCUIT_BREAKER_NAME);
                    return rcb.run(it, throwable -> Mono.just(customerId));
                });
    }
}
