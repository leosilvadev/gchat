package br.leosilvadev.gchat.notifiers

import org.springframework.messaging.simp.SimpMessagingTemplate

import spock.lang.Specification
import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.utils.ChatConstants

class UserLoginNotifierSpec extends Specification {

	UserLoginNotifier notifier
	
	def builder
	def template
	
	def setup(){
		builder  = Mock(MessageBuilder)
		template = Mock(SimpMessagingTemplate)
		notifier = new UserLoginNotifier(builder: builder, template: template)
	}
	
	def "Should notify a User Login"(){
		def chatUser = new ChatUser(name: "Fake", email: "fake@fake.com")
		
		given: "A room code and a user"
			def roomCode = "1234"
			def user 	 = new User(name:"Fake", email:"fake@fake.com")
			
		when: ""
			notifier.to(roomCode).by(user).send()
			
		then: ""
			1 * builder.newUser('Let\'s welcome <b>Fake</b>', chatUser, ChatConstants.NEW_USER)
			
	}
	
}
