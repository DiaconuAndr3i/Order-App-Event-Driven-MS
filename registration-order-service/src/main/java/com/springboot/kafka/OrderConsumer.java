package com.springboot.kafka;

import com.springboot.event.OrderEvent;
import com.springboot.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RegistrationOrderProducer registrationOrderProducer;

    @KafkaListener(topics = "${spring.kafka.topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrder(OrderEvent orderEvent) throws ExecutionException, InterruptedException {
        LOGGER.info(String.format("Message received => %s", orderEvent.toString()));

        orderService.saveOrder(orderEvent.getOrder());
        registrationOrderProducer.sendMessageToBroker(orderEvent);
    }
}
