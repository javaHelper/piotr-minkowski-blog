# Introduction to Reactive APIs with Postgres, R2DBC, Spring Data JDBC and Spring WebFlux

<img width="516" alt="Screenshot 2022-07-15 at 5 48 11 PM" src="https://user-images.githubusercontent.com/54174687/179221430-5f2dc0ce-b3ff-4f10-9de5-a5cf2fc7451a.png">

- Ref: https://piotrminkowski.com/2018/10/18/introduction-to-reactive-apis-with-postgres-r2dbc-spring-data-jdbc-and-spring-webflux/

GET -> `http://localhost:8011/organizations/1/withEmployees`

Response:

```json
{
    "id": 1,
    "name": "Mastercard",
    "employees": [
        {
            "id": 1,
            "name": "John Smith",
            "organizationId": 1
        },
        {
            "id": 2,
            "name": "Darren Hamilton",
            "organizationId": 1
        },
        {
            "id": 3,
            "name": "Tom Scott",
            "organizationId": 1
        }
    ]
}
```

- WebClient Code

```java
  package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private WebClient.Builder webClient;

	@GetMapping
	public Flux<Organization> findAll(){
		return organizationRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Organization> findById(@PathVariable Integer id){
		return organizationRepository.findById(id);
	}

	@GetMapping("/{id}/withEmployees")
	public Mono<OrganizationDTO> findByIdWithEmployees(@PathVariable Integer id){
		Flux<Employee> employeeFlux = webClient.build()
				.get()
				.uri("http://localhost:8012/employees/organization/"+ id)
				.retrieve()
				.bodyToFlux(Employee.class);
		
		Mono<Organization> orgMono = organizationRepository.findById(id);
		
		return orgMono.zipWith(employeeFlux.collectList())
				.log()
				.map(tuple -> new OrganizationDTO(tuple.getT1().getId(),tuple.getT1().getName(), tuple.getT2()));
	}
}

```
