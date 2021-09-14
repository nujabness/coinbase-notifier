package com.nujabness.notify.mail.impl;

import com.nujabness.notify.mail.IEmailsenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService implements IEmailsenderService {

	@Value("${spring.mail.username}") private String email;

	@Autowired 	private JavaMailSender mailSender;

	public void sendSimpleEmail(String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(email);
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
	}

	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom(email);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
		mailSender.send(mimeMessage);
	}
}