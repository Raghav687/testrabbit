package com.testazure.testazure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testazure.testazure.Response.Response;
import com.testazure.testazure.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api/test")
public class Controller {
    @Autowired
    RabbitMQProducer rabbitMQProducer;
    @GetMapping
    public Response<String> testRabbitMQ(@RequestParam("message") String message){
        Response<String> response = new Response<String>();
        rabbitMQProducer.sendMessage(message);
        response.setCode("200");
        response.setMessage("RabbitMQ worked");
        return response;
    }
}
