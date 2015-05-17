package br.leosilvadev.gchat.mail

import spock.lang.Specification
import br.leosilvadev.gchat.databases.managers.RedisManager
import br.leosilvadev.gchat.mail.dto.MailMessage
import br.leosilvadev.gchat.utils.ExtensionUtils

class MailMessageRegisterSpec extends Specification {
	
	MailMessageRegister register
	RedisManager manager
	
	def setup(){
		manager = Mock(RedisManager)
		
		register = new MailMessageRegister(redisManager: manager)
		
		ExtensionUtils.extendObjects()
	}
	
	def "Should register a Mail to be sent later"(){
		given: "A Mail Message to send"
			def message = new MailMessage(from: "fake@fake.com", to: "to@to.com", content: "Hello")
			
		when: "The Message is sent to register"
			register.register(message)
			
		then: "It should be registered in correct queue"
			1 * manager.execute(_ as Closure)
			
	}
	
}
