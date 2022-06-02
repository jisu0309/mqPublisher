package com.example.mqpublisher.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    private static final String EXCHANGE_NAME = "mqtopic.exchange";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/mq/queue")
    public ResponseEntity testPublish(@RequestParam("message") String message,
                                      @RequestParam("topic") String topic){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "mqtest.cook."+topic, message);
        return ResponseEntity.ok().build();
    }

}
