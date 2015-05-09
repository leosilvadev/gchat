package br.leosilvadev.gchat.mail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import br.leosilvadev.gchat.events.UserRegisteredEvent
import br.leosilvadev.gchat.utils.ChatConstants;

@Component
class MailMessageRegister implements ApplicationListener<UserRegisteredEvent> {
	
	public static final String QUEUE_MAILS_TO_SEND = "gchat:mails_to_send";

	@Autowired JedisPool jedisPool
	
	@Override
	void onApplicationEvent(UserRegisteredEvent event) {
		def user = event.source
		register new MailMessage(from: ChatConstants.SYSTEM_MAIL, to: user.email, content: "Welcome!")
	}
	
	def register(MailMessage mail){
		Jedis jedis = jedisPool.getResource()
		jedis.lpush(QUEUE_MAILS_TO_SEND, mail.toJson())
	}
	
}