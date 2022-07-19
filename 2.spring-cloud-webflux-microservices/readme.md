# Reactive Microservices with Spring WebFlux and Spring Cloud

![Reactive](https://user-images.githubusercontent.com/54174687/179053307-d2205738-1100-4fce-81d5-29466062ea2b.png)



- Eureka: http://localhost:8761/

<img width="1506" alt="Screenshot 2022-07-14 at 11 38 21 PM" src="https://user-images.githubusercontent.com/54174687/179052592-ac481864-5976-4def-b9fd-9e4ea1d8c9de.png">

```sh
curl --location --request POST 'http://localhost:3333/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "John",
    "lastName": "Scott",
    "age": 30
}'
```
Response:

```json
{
    "id": "62d04d913fe1f54c93490920",
    "firstName": "John",
    "lastName": "Scott",
    "age": 30,
    "accounts": null
}
```


```sh
curl --location --request POST 'http://localhost:8090/account' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "1234567890",
    "amount": 5000,
    "customerId": "62d04d913fe1f54c93490920"
}'
```

```sh
curl --location --request POST 'http://localhost:8090/account' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "1234567891",
    "amount": 12000,
    "customerId": "62d04d913fe1f54c93490920"
}'
```

```sh
curl --location --request POST 'http://localhost:8090/account' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "1234567892",
    "amount": 2000,
    "customerId": "62d04d913fe1f54c93490920"
}'
```

```sh
curl --location --request GET 'http://localhost:8090/customer/62d04d913fe1f54c93490920/with-accounts'
```

Response:
```json
{
    "id": "62d04d913fe1f54c93490920",
    "firstName": "John",
    "lastName": "Scott",
    "age": 30,
    "accounts": [
        {
            "id": "62d04e2342af3f385b6e62f4",
            "number": "1234567890",
            "amount": 5000
        },
        {
            "id": "62d04e5d42af3f385b6e62f5",
            "number": "1234567891",
            "amount": 12000
        },
        {
            "id": "62d04e9b42af3f385b6e62f6",
            "number": "1234567892",
            "amount": 2000
        }
    ]
}
```

- Ref: https://piotrminkowski.com/2018/05/04/reactive-microservices-with-spring-webflux-and-spring-cloud/

```java
@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository repository;
	@Autowired
    private WebClient.Builder webClientBuilder;

	@GetMapping("/{id}")
	public Mono<Customer> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id);
	}

	@GetMapping
	public Flux<Customer> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{id}/with-accounts")
	public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
		LOGGER.info("findByIdWithAccounts: id={}", id);
		Flux<Account> accounts = webClientBuilder
				.build()
				.get()
				.uri("http://account-service/account/customer/{customer}", id)
				.retrieve()
				.bodyToFlux(Account.class);	
		
		return accounts
				.collectList()
				.map(a -> new Customer(a))
				.mergeWith(repository.findById(id))
				.collectList()
				.map(CustomerMapper::map);
	}

	@PostMapping
	public Mono<Customer> create(@RequestBody Customer customer) {
		LOGGER.info("create: {}", customer);
		return repository.save(customer);
	}
	
}
```
