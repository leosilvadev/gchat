package br.leosilvadev.gchat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate

import spock.lang.Specification
import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.dto.Message
import br.leosilvadev.gchat.model.dto.PublicMessage

class ChatMessageControllerSpec extends Specification {

	def chatMessageController
	def template
	def messageBuilder
	
	def setup(){
		chatMessageController = new ChatMessageController()
		
		template = Mock(SimpMessagingTemplate)
		messageBuilder = Mock(MessageBuilder)
		chatMessageController.template  = template
		chatMessageController.builder = messageBuilder
	}
	
	def "Should send a Public Message"(){
		given: "A client message"
			Message message = new Message(from:"User", content:"Hello")
			PublicMessage pubMessage = new PublicMessage()
		
		when: "The client send a new Message"
			chatMessageController.handlePublicMessage(message, "FAKE_ROOM")
		
		then: "Message is send to all users in the Room"
			1 * messageBuilder.publicMessage(message, "FAKE_ROOM") >> pubMessage
			1 * template.convertAndSend("/topic/rooms-FAKE_ROOM", pubMessage)
	}
	
}
