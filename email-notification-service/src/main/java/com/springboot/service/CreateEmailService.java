package com.springboot.service;

import com.springboot.event.OrderEvent;
import com.springboot.model.EmailFields;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CreateEmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailFields createEmail(OrderEvent orderEvent){
        EmailFields email = new EmailFields();
        email.setToEmail(orderEvent.getOrder().getEmail());
        email.setFromEmail(fromEmail);
        String text = "Your order has been registered. You will receive more details about this in the following.";
        email.setBody(text);
        email.setSubject("Order " + orderEvent.getOrder().getName());

        return email;
    }
}
