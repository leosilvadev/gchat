package br.leosilvadev.gchat.model.mail

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

@Component
class MailMessageHandler {

	@Autowired JedisPool jedisPool
	@Autowired MailMessageSender mailMessageSender
	
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
				println message
				def mailMessage = message.toObject(MailMessage.class)
				println mailMessage
			}
		}
	}
	
}
