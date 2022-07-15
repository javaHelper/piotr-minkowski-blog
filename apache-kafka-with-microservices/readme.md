# Apache Kafka with Microservices

<img width="507" alt="Screenshot 2022-07-15 at 9 38 16 PM" src="https://user-images.githubusercontent.com/54174687/179263257-44e70304-1006-46f0-bafe-7313029ea6ae.png">

In this exaple, we've used Spring Boot and Apache Kafka for Event Driven based microservices



POST -> 
- When we create Blogs record through API, an event raise into Kafka and it will be consumed by `blog-comment-service`.
- Also, When we create Blogs-Comments record through API, an event raise into Kafka and it will be consumed by `blog-service`.


```sh
curl --location --request POST 'http://localhost:8012/blogs/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title" : "Spring in Action",
    "image" : "http://my-image/learning-java",
    "description" : "Helps to learn Spring Core concepts"
}'
```
Response:

```json
{
    "id": 1,
    "title": "Spring in Action",
    "image": "http://my-image/learning-java",
    "description": "Helps to learn Spring Core concepts"
}
```

POST -> 

```sh
curl --location --request POST 'http://localhost:8011/blog-comment/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "blogId" : 1,
    "message" : "This is very good book to learn Spring Framework"
}'
```

Response:

```json
{
    "id": 1,
    "blogId": 1,
    "message": "This is very good book to learn Spring Framework"
}
```

<img width="1203" alt="Screenshot 2022-07-15 at 9 46 24 PM" src="https://user-images.githubusercontent.com/54174687/179264681-3ea8a4f5-155a-4a01-ba9d-ec06a9992f90.png">


