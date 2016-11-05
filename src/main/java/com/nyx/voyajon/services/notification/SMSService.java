package com.nyx.voyajon.services.notification;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service
public class SMSService {
	
	
	
	@Autowired
	private SMSSender smsSender;
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	//@Async
	public void sendSMS(final String[] to, final String subject, Map<String, Object> model, String template){
		try {
			String message;
			message = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template), model);
			smsSender.send(to,subject,message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
