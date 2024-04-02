package com.testazure.testazure.Config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;



@Configuration
public class RabbitMQConfig {
    @Value("queue")
    private String queue;

    @Value("json_queue")
    private String jsonQueue;

    @Value("exchange")
    private String exchange;

    @Value("key")
    private String routingKey;

    @Value("json_key")
    private String routingJsonKey;

     
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    
   
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
   
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }


    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}