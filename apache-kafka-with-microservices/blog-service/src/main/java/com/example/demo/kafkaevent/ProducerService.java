package com.example.demo.kafkaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

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
