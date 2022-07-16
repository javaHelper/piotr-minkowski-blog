package com.example.demo.kafkaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.model.NotificationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendNotification(Customer c) throws JsonProcessingException {
		NotificationRequest notificationRequest = new NotificationRequest(c.getId(), c.getEmail(),
				"Hello, buddy!");
		
		String json = objectMapper.writeValueAsString(notificationRequest);

		kafkaTemplate.send("customer-topic", json);
	}
}
