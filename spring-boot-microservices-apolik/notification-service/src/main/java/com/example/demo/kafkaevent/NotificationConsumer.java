package com.example.demo.kafkaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationConsumer {

	@Autowired
	private NotificationService notificationService;
	
	@KafkaListener(topics = "customer-topic")
	public void listen(String notificationRequest) throws JsonMappingException, JsonProcessingException {
        log.info("Message received: {}", notificationRequest);
        
        notificationService.send(notificationRequest);
    }
}
