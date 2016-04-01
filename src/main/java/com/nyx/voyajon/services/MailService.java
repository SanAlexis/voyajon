package com.nyx.voyajon.services;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class MailService {
	
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	@Async
	public void sendEmail(final String from, final String[] to, final String subject, Map<String, Object> model, String template){
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
			message.setFrom(from, "VOYAJON");
			message.setTo(to);
			message.setSubject(subject);
			String text;
			text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template), model);
			message.setText(text, true);
			mailSender.send(mimeMessage);
		} catch (IOException | TemplateException | MessagingException e) {
			e.printStackTrace();
		}
	}

}
