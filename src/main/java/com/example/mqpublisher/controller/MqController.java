package com.example.mqpublisher.controller;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    private static final String EXCHANGE_NAME = "mqtopic.exchange";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @GetMapping("/mq/queue")
    public ResponseEntity testPublish(@RequestParam("message") String message,
                                      @RequestParam("topic") String topic){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "mqtest.cook."+topic, message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/queue")
    public ResponseEntity createQueue(@RequestParam("queueName") String queueName,
                                      @RequestParam("routingKey") String routingKey){
        Queue queue = new Queue(queueName, true, false, false);
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, EXCHANGE_NAME, routingKey, null);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
        return ResponseEntity.ok().build();
    }

}
