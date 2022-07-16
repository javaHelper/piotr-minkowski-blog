# Spring Boot Microservices:

![microservices](https://user-images.githubusercontent.com/54174687/179351220-54e2bad3-a0cc-426b-975d-f51de83bebf7.png)

<img width="1404" alt="Screenshot 2022-07-16 at 3 47 19 PM" src="https://user-images.githubusercontent.com/54174687/179350767-35297708-5df2-4b09-b251-0654df532604.png">

# Sequence to start microservices

- config-server
- discovery-server
- customer-service
- fraud-service
- notification-service
- gateway-service

GET -> `localhost:8011/fraud/api/v1/fraud-check/1`

Response:

```json
{
    "fraudster": false
}
```

POST -> 

```
curl --location --request POST 'http://localhost:8011/customer/api/v1/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@gmail.com"
}'
```

<img width="1214" alt="Screenshot 2022-07-16 at 3 57 43 PM" src="https://user-images.githubusercontent.com/54174687/179351173-9ed87f98-f44e-4df8-b2a4-84ae0a119eba.png">

# Zipkin: 

http://localhost:9411/

<img width="1511" alt="Screenshot 2022-07-16 at 3 50 30 PM" src="https://user-images.githubusercontent.com/54174687/179350892-006d0589-3881-4749-b9b8-48e7004f43fe.png">

---------------------------

# How to test Dynamic Configuration changes?

```
http://localhost:8011/customer/api/v1/customers/status/check
```


POST -> `http://localhost:8012/actuator/busrefresh`

- Check the configurations updated 
```
http://localhost:8011/customer/api/v1/customers/status/check
```
