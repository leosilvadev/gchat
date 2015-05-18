package br.leosilvadev.gchat.mail

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.constants.ChatConstants
import br.leosilvadev.gchat.constants.MailConstants
import br.leosilvadev.gchat.databases.managers.RedisManager
import br.leosilvadev.gchat.events.UserRegisteredEvent
import br.leosilvadev.gchat.mail.dto.MailMessage

@Component
class MailMessageRegister implements ApplicationListener<UserRegisteredEvent> {
	
	@Autowired RedisManager redisManager
	
	void onApplicationEvent(UserRegisteredEvent event) {
		def user = event.source
		register new MailMessage(from: ChatConstants.SYSTEM_MAIL, to: user.email, content: "Welcome!")
	}
	
	def register(MailMessage mail){
		redisManager.execute({jedis ->
			jedis.lpush(MailConstants.QUEUE_MAILS_TO_SEND, mail.toJson())
		})
	}
	
}
