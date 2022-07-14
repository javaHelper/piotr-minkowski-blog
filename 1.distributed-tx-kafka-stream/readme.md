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
<img width="1193" alt="Screenshot 2022-07-14 at 7 19 54 PM" src="https://user-images.githubusercontent.com/54174687/178998010-e1ca929d-a513-4728-8de8-1789c726c1b8.png">


- oders-KSTREAM-JOINOTHER

`{"id":1,"customerId":10,"productId":10,"productCount":5,"price":100,"status":"ACCEPT","source":null}`

- orders-KSTREAM-JOINOTHER 

`{"id":1,"customerId":10,"productId":10,"productCount":5,"price":100,"status":"ACCEPT","source":"payment"}`

- orders-orders-changelog

`{"id":1,"customerId":10,"productId":10,"productCount":5,"price":100,"status":"NEW","source":null}`

payment-orders

`{"id":1,"customerId":10,"productId":10,"productCount":5,"price":100,"status":"ACCEPT","source":"payment"}`

- stock-orders

`{"id":1,"customerId":10,"productId":10,"productCount":5,"price":100,"status":"ACCEPT","source":null}`

