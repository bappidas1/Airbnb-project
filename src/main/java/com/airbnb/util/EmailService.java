package com.airbnb.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmailWithAttachment(String toEmail, String subject, String text, File attachmentFile) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // The 'from' field is automatically populated from spring.mail.username in application.properties
        helper.setFrom("bappidas.agt@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);

        if (attachmentFile != null && attachmentFile.exists()) {
            FileSystemResource fileResource = new FileSystemResource(attachmentFile);
            helper.addAttachment(fileResource.getFilename(), fileResource);
        }

        mailSender.send(mimeMessage);
    }
}
