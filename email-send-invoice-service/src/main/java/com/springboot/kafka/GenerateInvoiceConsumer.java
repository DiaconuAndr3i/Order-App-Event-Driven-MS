package com.springboot.kafka;

import com.springboot.event.InvoiceEvent;
import com.springboot.service.CreateEmailService;
import com.springboot.service.SendEmailInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class GenerateInvoiceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateInvoiceConsumer.class);

    @Autowired
    private CreateEmailService createEmailService;

    @Autowired
    private SendEmailInvoiceService sendEmailInvoiceService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeGenerateInvoice(InvoiceEvent invoiceEvent) throws MessagingException {
        LOGGER.info(String.format("Message received => %s", invoiceEvent.toString()));

        sendEmailInvoiceService.sendEmail(createEmailService.createEmail(invoiceEvent));
    }
}
