# Apache Kafka with Microservices

<img width="507" alt="Screenshot 2022-07-15 at 9 38 16 PM" src="https://user-images.githubusercontent.com/54174687/179263257-44e70304-1006-46f0-bafe-7313029ea6ae.png">

- blog-service

```java
@Service
@Slf4j
public class ProducerService {

	private static final String TOPIC = "blog";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	public void sendMessage(String message) {
        log.info(String.format("### Message send -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}

@Service
@Slf4j
public class ConsumerService {

	
	@KafkaListener(topics = "blog_comment", groupId = "0")
	public void consume(String message) {
		log.info("-------------------------------------");
        log.info(String.format("### Message received -> %s", message));
    }
}
```

```properties
#
server.port=8012
spring.application.name=blog-service


#MySQL DB
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true


#Kafka Consumer
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=0
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

# Kafka Producer
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

```


- blog-comment-service

```java
@Slf4j
@Service
public class ProducerService {
	private static final String TOPIC = "blog_comment";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String message) {
		log.info(String.format("### Message send -> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
}

@Service
public class ConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaListener(topics = "blog", groupId = "0")
	public void consume(String message) {
		LOGGER.info("-----------------------------------------------");
		LOGGER.info(String.format("### Message received -> %s", message));
	}
}
```

```yml
#
server.port=8011
spring.application.name=blog-service

#MySQL DB
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

#Consumer
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=0
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

# Producer
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

```




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


```sh
kafka-console-consumer --bootstrap-server localhost:9092 --topic blog --from-beginning
This BlogEntity(id=1, title=Spring in Action, image=http://my-image/learning-java, description=Helps to learn Spring Core concepts) blog is created !

kafka-console-consumer --bootstrap-server localhost:9092 --topic blog_comment --from-beginning
This BlogCommentEntity(id=1, blogId=1, message=This is very good book to learn Spring Framework) comment is created !
```
