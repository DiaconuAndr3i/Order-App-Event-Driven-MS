package com.springboot.service;

import com.springboot.model.SMSMessage;
import com.springboot.twilio.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Autowired
    private final TwilioConfig twilioConfig;

    public SMSService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public String sendSMS(SMSMessage smsMessage){
        Message message = Message.creator(
                        new PhoneNumber(smsMessage.getPhoneNumber()),
                        new PhoneNumber(twilioConfig.getFromNumber()),
                        smsMessage.getMessage())
                .create();

        return message.getStatus().toString();
    }
}
