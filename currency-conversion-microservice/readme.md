# Currency Conversion Microservices

<img width="527" alt="Screenshot 2022-07-16 at 6 23 01 PM" src="https://user-images.githubusercontent.com/54174687/179355711-9a53742f-169d-4ffa-8792-eaaabc6463e4.png">

<img width="1212" alt="Screenshot 2022-07-16 at 6 25 16 PM" src="https://user-images.githubusercontent.com/54174687/179355798-125f74bf-2987-47ec-8e6d-9fe8136091cb.png">


GET -> `http://localhost:8001/currency-conversion/from/USD/to/INR/quantity/1`

Response:

```json
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "quantity": 1,
    "conversionMultiple": 65.00,
    "totalCalculatedAmount": 65.00,
    "environment": "8002 rest template"
}
```

GET -> `http://localhost:8001/currency-conversion-feign/from/USD/to/INR/quantity/1`

Response:

```json
{
    "id": 10001,
    "from": "USD",
    "to": "INR",
    "quantity": 1,
    "conversionMultiple": 65.00,
    "totalCalculatedAmount": 65.00,
    "environment": "8002 feign"
}
```
