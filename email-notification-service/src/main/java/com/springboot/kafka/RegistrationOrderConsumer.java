package com.springboot.kafka;

import com.springboot.event.OrderEvent;
import com.springboot.service.CreateEmailService;
import com.springboot.service.SendEmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationOrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationOrderConsumer.class);
    @Autowired
    private CreateEmailService createEmailService;
    @Autowired
    private SendEmailNotificationService sendEmailNotificationService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRegistrationOrder(OrderEvent orderEvent){
        LOGGER.info(String.format("Message received => %s", orderEvent.toString()));

        sendEmailNotificationService.sendNotificationEmail(createEmailService.createEmail(orderEvent));
    }
}
