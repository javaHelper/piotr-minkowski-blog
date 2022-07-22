# spring-boot-webclient-resilience4j

<img width="404" alt="Screenshot 2022-07-22 at 3 19 41 PM" src="https://user-images.githubusercontent.com/54174687/180413838-05faa393-805e-4892-b339-e8c976805144.png">

# Customer-service-client

```properties
server.port=8600

management.endpoints.web.base-path=/monitoring
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
management.health.circuitbreakers.enabled=true


# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/ABC_app?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
#spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

# Resilience 4J
resilience4j.circuitbreaker.instances.customer-service.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.customer-service.minimum-number-of-calls=10
resilience4j.circuitbreaker.instances.customer-service.sliding-window-type=time-based
resilience4j.circuitbreaker.instances.customer-service.sliding-window-size=10
resilience4j.circuitbreaker.instances.customer-service.wait-duration-in-open-state=50s
resilience4j.circuitbreaker.instances.customer-service.permitted-number-of-calls-in-half-open-state=3
```

- Controller

```
public class WebClientConfiguration {

	@Bean
	public WebClient getWebClient() {
		return WebClient.builder().baseUrl("http://localhost:8500")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
}

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
```

GET -> `http://localhost:8600/customers/1`

POST -> 

```
curl --location --request POST 'http://localhost:8600/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Harshita",
    "lastName": "Dekate",
    "address": "Achalpur"
}'
```
