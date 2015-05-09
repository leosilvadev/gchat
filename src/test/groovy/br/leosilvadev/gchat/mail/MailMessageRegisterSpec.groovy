package br.leosilvadev.gchat.mail

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import spock.lang.Specification
import br.leosilvadev.gchat.model.mail.MailMessage
import br.leosilvadev.gchat.model.mail.MailMessageRegister
import br.leosilvadev.gchat.utils.ExtensionUtils

class MailMessageRegisterSpec extends Specification {
	
	MailMessageRegister register
	JedisPool jedisPool
	Jedis jedis
	
	def setup(){
		jedisPool = Mock(JedisPool)
		jedis = Mock(Jedis)
		
		register = new MailMessageRegister(jedisPool: jedisPool)
		
		ExtensionUtils.extendObjects()
	}
	
	def "Should register a Mail to be sent later"(){
		given: "A Mail Message to send"
			def message = new MailMessage(from: "fake@fake.com", to: "to@to.com", content: "Hello")
			
		when: "The Message is sent to register"
			register.register(message)
			
		then: "It should be registered in correct queue"
			1 * jedisPool.getResource() >> jedis
			1 * jedis.lpush(MailMessageRegister.QUEUE_MAILS_TO_SEND, message.toJson())
			
	}
	
}
