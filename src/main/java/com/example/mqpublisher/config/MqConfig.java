package com.example.mqpublisher.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class MqConfig {

//    private static final String EXCHANGE_NAME = "amq.";
//    private static final String QUEUE_NAME = "mqtest.queue";
//    private static final String ROUTING_KEY = "mqtest.cook.#";

//    @Bean
//    TopicExchange exchange(){
//        return new TopicExchange(EXCHANGE_NAME);
//    }

//    @Bean
//    Queue queue(){
//        return new Queue(QUEUE_NAME);
//    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }


}
