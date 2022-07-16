package com.fullquackdeveloper.subscriberservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubscriberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriberServiceApplication.class, args);
	}

}
