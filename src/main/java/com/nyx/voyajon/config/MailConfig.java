package com.nyx.voyajon.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@EnableAsync
@PropertySource("classpath:app.properties")
public class MailConfig {
	
	private final Logger log = LoggerFactory.getLogger(MailConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender javaMailSender(){
		log.debug("configuration mail server start");
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(env.getProperty("mail.smtp.host"));
		sender.setPort(env.getProperty("mail.smtp.port", Integer.class));
		sender.setUsername(env.getProperty("mail.smtp.user"));
		sender.setPassword(env.getProperty("mail.smtp.password"));
		sender.setProtocol("smtp");
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth",true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.smtp.ssl.trust", env.getProperty("mail.smtp.host"));
		sender.setJavaMailProperties(javaMailProperties);
		log.debug("configuration mail server end");
		return sender;
		
	}
	
	@Bean(name = "freemarkerConfiguration")
	public FreeMarkerConfigurationFactoryBean freemarkerConfig(){
		FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
		freemarkerConfiguration.setTemplateLoaderPath("classpath:templates");
		return freemarkerConfiguration;
	}
	

}
