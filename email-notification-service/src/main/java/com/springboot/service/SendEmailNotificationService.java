package com.springboot.service;

import com.springboot.model.EmailFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotificationEmail(EmailFields emailFields){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(emailFields.getFromEmail());
        simpleMailMessage.setTo(emailFields.getToEmail());
        simpleMailMessage.setText(emailFields.getBody());
        simpleMailMessage.setSubject(emailFields.getSubject());

        mailSender.send(simpleMailMessage);

        System.out.println("Email sent");
    }
}
