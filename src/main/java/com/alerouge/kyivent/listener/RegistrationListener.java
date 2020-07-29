package com.alerouge.kyivent.listener;

import java.util.UUID;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.alerouge.kyivent.event.OnRegistrationCompleteEvent;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.service.user.UserService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final UserEntity user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    //

    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final UserEntity user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/user/registrationConfirm?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("support@eventup.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom(env.getProperty("support.email"));
        return email;
    }
    

}
