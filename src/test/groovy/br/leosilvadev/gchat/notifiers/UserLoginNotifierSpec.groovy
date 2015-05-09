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
		def newUserMessage
		
		given: "A room code and a user"
			def roomCode = "1234"
			def user 	 = new User(name:"Fake", email:"fake@fake.com")
			
		when: "The the notifier receives a room code, a user and send"
			notifier.to(roomCode).by(user).send()
			
		then: "A 'welcome' message must be sent to users in the room"
			1 * builder.newUser('Let\'s welcome <b>Fake</b>', chatUser, ChatConstants.SYSTEM_MESSAGE) >> newUserMessage
			1 * template.convertAndSend("/topic/rooms-1234", newUserMessage)
	}
	
}
