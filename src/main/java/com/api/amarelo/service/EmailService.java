package com.api.amarelo.service;

import com.api.amarelo.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("library@gmail.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());
        javaMailSender.send(message);
    }

}