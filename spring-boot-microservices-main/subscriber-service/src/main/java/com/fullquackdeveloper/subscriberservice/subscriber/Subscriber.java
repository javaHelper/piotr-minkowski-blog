package com.fullquackdeveloper.subscriberservice.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Subscriber {
    @RabbitListener(queues = "hello-queue")
    public void getMessage(final String message) {
        log.info("Getting messages... " + message);
    }
}
