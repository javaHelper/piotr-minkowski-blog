package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class BlogCommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogCommentServiceApplication.class, args);
	}

}
