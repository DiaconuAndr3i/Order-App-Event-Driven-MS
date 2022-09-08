package com.springboot.controller;

import com.springboot.event.OrderEvent;
import com.springboot.model.EmailFields;
import com.springboot.service.CreateEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${spring.mail.username}")
    private String username;


    @GetMapping("/emailNotify")
    public String getUsername(){
        EmailFields emailFields = new EmailFields();
        return emailFields.getFromEmail();
    }
}
