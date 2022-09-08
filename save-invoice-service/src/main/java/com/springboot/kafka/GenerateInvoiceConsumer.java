package com.springboot.kafka;

import com.springboot.event.InvoiceEvent;
import com.springboot.service.DBPDFInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GenerateInvoiceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateInvoiceConsumer.class);

    @Autowired
    private DBPDFInvoiceService dbpdfInvoiceService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeGenerateInvoice(InvoiceEvent invoiceEvent) throws IOException {
        LOGGER.info(String.format("Message received => %s", invoiceEvent.toString()));

        Path path = Paths.get(invoiceEvent.getPathInvoice());
        byte[] dataFile = Files.readAllBytes(path);

        dbpdfInvoiceService.savePdf(invoiceEvent, dataFile);

    }
}
