package com.airbnb.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.whatsapp.sandbox.number}")
    private String fromWhatsAppNumber;


    public void sendWhatsAppMessageWithMedia(String toPhoneNumber, String messageBody, String mediaUrl) {
        Message.creator(
                        new PhoneNumber("whatsapp:" + toPhoneNumber),
                        new PhoneNumber(fromWhatsAppNumber),
                        messageBody
                ).setMediaUrl(mediaUrl)
                .create();
    }
}
