package com.springboot.twilio;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    @Autowired
    private TwilioConfig twilioConfig;

    public TwilioInitializer(TwilioConfig twilioConfig){
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        System.out.println("Twilio initialized");
    }
}
