package com.nujabness.notify.mail;


import org.springframework.messaging.MessagingException;

public interface IEmailsenderService {

    void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException, javax.mail.MessagingException;
    void sendSimpleEmail(String toEmail, String body, String subject);
}
