package com.springboot.kafka;

import com.springboot.event.InvoiceEvent;
import com.springboot.event.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class GenerateInvoiceProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateInvoiceProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public GenerateInvoiceProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToBroker(InvoiceEvent invoiceEvent){
        LOGGER.info(String.format("MESSAGE SENT => %s", invoiceEvent.toString()));

        Message<InvoiceEvent> message = MessageBuilder
                .withPayload(invoiceEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
