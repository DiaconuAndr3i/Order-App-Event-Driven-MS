package com.springboot.service;

import com.springboot.event.InvoiceEvent;
import com.springboot.model.EmailFields;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateEmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailFields createEmail(InvoiceEvent invoiceEvent){
        EmailFields email = new EmailFields();
        email.setToEmail(invoiceEvent.getOrderEvent().getOrder().getEmail());
        email.setFromEmail(fromEmail);
        String text = "Below you will find the invoice corresponding to your order.";
        email.setBody(text);
        email.setSubject("Invoice " + invoiceEvent.getOrderEvent().getOrder().getName());
        email.setAttachment(invoiceEvent.getPathInvoice());

        return email;
    }
}
