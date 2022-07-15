package com.example.demo.kafkaevent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {

	
	@KafkaListener(topics = "blog_comment", groupId = "0")
	public void consume(String message) {
		log.info("-------------------------------------");
        log.info(String.format("### Message received -> %s", message));
    }
}
