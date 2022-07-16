package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationRequest;
import com.example.demo.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void send(String notificationrequest) throws JsonMappingException, JsonProcessingException {
		System.out.println("Notification Received "+ notificationrequest);
		
		NotificationRequest request = objectMapper.readValue(notificationrequest, NotificationRequest.class);
		
		
        Notification notification = Notification.builder()
                .message(request.getMessage())
                .toCustomerId(request.getToCustomerId())
                .toCustomerEmail(request.getToCustomerEmail())
                .sender("PADNI")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
