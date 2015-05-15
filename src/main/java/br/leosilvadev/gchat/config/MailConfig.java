package br.leosilvadev.gchat.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Value("${mail.transport}")
	private String transport;

	@Value("${mail.smtp.host}")
	private String smtpHost;

	@Value("${mail.smtp.port}")
	private String smtpPort;

	@Value("${mail.smtp.auth}")
	private boolean smtpAuth;
	
	@Value("${mail.smtp.starttls}")
	private boolean starttls;

	@Value("${mail.smtp.enablessl}")
	private boolean enableSSL;

	@Value("${mail.debug}")
	private boolean debug;
	

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.smtp.socketfactory.port}")
	private String socketPort;

	@Value("${mail.smtp.socketfactory.class}")
	private String socketClass;
	
	@Value("${mail.smtp.socketfactory.fallback}")
	private String socketFallback;

	@Bean
	public JavaMailSender mailSender() {
		Properties props = new Properties();
	    props.put("mail.transport.protocol", transport);
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.EnableSSL.enable", enableSSL);
		props.put("mail.debug", debug);
		
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", socketClass); 
		props.setProperty("mail.smtp.socketFactory.fallback", socketFallback); 
		props.setProperty("mail.smtp.socketFactory.port", socketPort); 
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
	    sender.setHost(smtpHost);
	    sender.setUsername(username);
	    sender.setPassword(password);
	    sender.setJavaMailProperties(props);
	    
	    return sender;
	}

}
