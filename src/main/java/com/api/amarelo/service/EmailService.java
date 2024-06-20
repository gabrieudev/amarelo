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

    public void send(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("amarelo@gmail.com");
        simpleMailMessage.setText(email.body());
        simpleMailMessage.setSubject(email.subject());
        simpleMailMessage.setTo(email.to());
        javaMailSender.send(simpleMailMessage);
    }

}
