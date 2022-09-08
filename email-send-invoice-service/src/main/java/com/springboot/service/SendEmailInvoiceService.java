package com.springboot.service;

import com.springboot.model.EmailFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
public class SendEmailInvoiceService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailFields emailFields) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(emailFields.getFromEmail());
        mimeMessageHelper.setTo(emailFields.getToEmail());
        mimeMessageHelper.setText(emailFields.getBody());
        mimeMessageHelper.setSubject(emailFields.getSubject());

        FileSystemResource fileSystemResource = new FileSystemResource(new File(emailFields.getAttachment()));

        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);

        mailSender.send(mimeMessage);

        System.out.println("Email sent");
    }
}
