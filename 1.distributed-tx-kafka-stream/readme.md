# Distributed Transactions in Microservices with Kafka Streams and Spring Boot

![Distributed-tx-kafka-stream](https://user-images.githubusercontent.com/54174687/178996878-5120b54f-f57b-4543-93d4-efe607d4480a.jpg)

POST ->

```sh
curl --location --request POST 'http://localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
  "customerId": 10,
  "productId": 10,
  "productCount": 5,
  "price": 100,
  "status": "NEW"
}'
```

Response:

```json
{
    "id": 1,
    "customerId": 10,
    "productId": 10,
    "productCount": 5,
    "price": 100,
    "status": "NEW",
    "source": null
}
```

GET -> `http://localhost:8080/orders`

Response:

```json
[
    {
        "id": 1,
        "customerId": 10,
        "productId": 10,
        "productCount": 5,
        "price": 100,
        "status": "NEW",
        "source": null
    }
]
```

