package com.springboot.kafka;

import com.springboot.event.OrderEvent;
import com.springboot.service.CreateSMSMessageService;
import com.springboot.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationOrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationOrderConsumer.class);

    @Autowired
    private CreateSMSMessageService createSMSMessageService;
    @Autowired
    private SMSService smsService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRegistrationOrder(OrderEvent orderEvent){
        LOGGER.info(String.format("Message received => %s", orderEvent.toString()));

        smsService.sendSMS(createSMSMessageService.createSMSMessage(orderEvent));
    }
}
