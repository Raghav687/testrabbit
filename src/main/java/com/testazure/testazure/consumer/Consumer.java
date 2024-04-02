package com.testazure.testazure.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "queue")
    public void receive(String message) {
        log.info(String.format("Received message -> %s", message));
    }
}