package br.leosilvadev.gchat.model.mail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.events.MailMessageReadEvent

@Component
class MailMessageSender implements ApplicationListener<MailMessageReadEvent> {

	@Autowired JavaMailSender javaMailSender
	
	@Override
	void onApplicationEvent(MailMessageReadEvent event) {
		send(event.source)
	}
	
	def send(MailMessage message){
		SimpleMailMessage mailMessage = new SimpleMailMessage()
		mailMessage.setTo(message.to)
		mailMessage.setFrom(message.from)
		mailMessage.setSubject(message.subject)
		mailMessage.setText(message.content)
		javaMailSender.send(mailMessage)
		return mailMessage;
	}
	
}
