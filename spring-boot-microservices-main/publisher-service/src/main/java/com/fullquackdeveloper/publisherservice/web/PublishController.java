package com.fullquackdeveloper.publisherservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/publish")
@RequiredArgsConstructor
public class PublishController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public void publishMessage()
    {
        rabbitTemplate.convertAndSend("hello-queue", "Hello World");
    }
}
