package com.nyx.voyajon.config;

import com.nyx.voyajon.entities.notification.MailSetting;
import com.nyx.voyajon.repositories.MailSettingRepository;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@EnableAsync
public class MailConfig {

    private final Logger log = LoggerFactory.getLogger(MailConfig.class);

    @Autowired
    private MailSettingRepository msr;

    @Bean
    public JavaMailSender javaMailSender() {
        log.debug("configuration mail server start");
        List<MailSetting> mss = msr.findAll();
        MailSetting ms = mss.isEmpty() ? null : mss.get(0);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(ms == null ? "auth.smtp.1and1.fr" : ms.getHostname());
        sender.setPort(ms == null ? 465 : ms.getPort());
        sender.setUsername(ms == null ? "arthur.tchipnang@twinsol.com" : ms.getUsername());
        sender.setPassword(ms == null ? "yohTh9ch" : ms.getPassword());
//        sender.setProtocol("smtps");
        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.auth", true);
//        javaMailProperties.put("mail.smtp.starttls.enable", true);
//        javaMailProperties.put("mail.smtp.ssl.trust", sender.getHost());
        
        javaMailProperties.put("mail.debug", true);

        javaMailProperties.put("mail.smtp.host", sender.getHost());
        javaMailProperties.put("mail.smtp.socketFactory.port", sender.getPort());
        javaMailProperties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.port", sender.getPort());
        sender.setJavaMailProperties(javaMailProperties);
        log.debug("configuration mail server end");
        return sender;

    }

    @Bean(name = "freemarkerConfiguration")
    public FreeMarkerConfigurationFactoryBean freemarkerConfig() {
        FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
        freemarkerConfiguration.setTemplateLoaderPath("/WEB-INF/notification");
        return freemarkerConfiguration;
    }

}
