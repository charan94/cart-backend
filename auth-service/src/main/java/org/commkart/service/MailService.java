package org.commkart.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.commkart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(User user, String pass) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("name", user.getFirstName() + " " + user.getLastName());
		context.setVariable("email", user.getEmail());
		context.setVariable("password", pass);
		String html = templateEngine.process("register", context);

		helper.setTo(user.getEmail());
		helper.setFrom("sai.charan@winvest-global.com");
		helper.setSubject("Registered");
		helper.setText(html, true);
		emailSender.send(message);
	}
}
