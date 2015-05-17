package br.leosilvadev.gchat.mail

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import br.leosilvadev.gchat.constants.MailConstants;
import br.leosilvadev.gchat.events.MailMessageReadEvent
import br.leosilvadev.gchat.events.Publisher
import br.leosilvadev.gchat.mail.dto.MailMessage;

@Component
class MailMessageHandler {

	@Autowired JedisPool jedisPool
	@Autowired Publisher publisher
	
	private boolean shouldListen
	private Jedis jedis
	
	@PostConstruct
	def init(){
		try{
			jedis = jedisPool.getResource()
			shouldListen = true
			
		}catch(e){
			
		}
	}
	
	@Async
	def listen(){
		while(shouldListen){
			List jsonMessages = jedis.blpop(0, MailConstants.QUEUE_MAILS_TO_SEND)
			jsonMessages.subList(1, jsonMessages.size()).each { message ->
				def mailMessage = message.toObject MailMessage.class
				publisher.publish new MailMessageReadEvent(mailMessage)
			}
		}
	}
	
	@PreDestroy
	def destroy(){
		shouldListen = false
		jedis.close()
	}
	
}
