package com.springboot.service;

import com.springboot.event.OrderEvent;
import com.springboot.model.SMSMessage;
import org.springframework.stereotype.Service;

@Service
public class CreateSMSMessageService {
    public SMSMessage createSMSMessage(OrderEvent orderEvent){
        SMSMessage smsMessage = new SMSMessage();
        smsMessage.setPhoneNumber(orderEvent.getOrder().getPhoneNumber());
        smsMessage.setMessage("Your order has been successfully registered, you will receive by email details about it + the invoice.");
        return smsMessage;
    }
}
