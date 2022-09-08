package com.springboot.kafka;

import com.springboot.event.InvoiceEvent;
import com.springboot.event.OrderEvent;
import com.springboot.model.PathNameFile;
import com.springboot.service.PDFGenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegistrationOrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationOrderConsumer.class);

    @Autowired
    private PDFGenerateService pdfGenerateService;

    @Autowired
    private GenerateInvoiceProducer generateInvoiceProducer;

    @KafkaListener(topics = "${spring.kafka.topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRegistrationOrder(OrderEvent orderEvent) throws IOException {
        LOGGER.info(String.format("Message received => %s", orderEvent.toString()));

        PathNameFile pathNameFile = pdfGenerateService.createInvoice(orderEvent);

        InvoiceEvent invoiceEvent = new InvoiceEvent();
        invoiceEvent.setOrderEvent(orderEvent);
        invoiceEvent.setPathInvoice(pathNameFile.getPathFile());
        invoiceEvent.setNameInvoice(pathNameFile.getNameFile());

        generateInvoiceProducer.sendMessageToBroker(invoiceEvent);
    }
}
