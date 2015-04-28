package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate

import spock.lang.Specification
import br.leosilvadev.gchat.FakePrincipal
import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.dto.Message
import br.leosilvadev.gchat.model.dto.PrivateMessage;
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
		PublicMessage pubMessage = new PublicMessage()
		ResponseEntity response
			
		given: "A client message"
			Message message = new Message(content: "FAKE MESSAGE", sentAt:"10:10:10" )
			
		when: "The client send a new Message to a Specific Room"
			response = chatMessageController.handlePublicMessage(message, "FAKE_ROOM", new FakePrincipal("FAKE_SENDER"))
		
		then: "Message is send to all users in the Room"
			1 * messageBuilder.publicMessage(message, "FAKE_ROOM", "FAKE_SENDER") >> pubMessage
			1 * template.convertAndSend("/topic/rooms-FAKE_ROOM", pubMessage)
			HttpStatus.CREATED == response.statusCode
	}
	
	
	
	def "Should send a Private Message"(){
		PrivateMessage privMessage = new PrivateMessage()
		ResponseEntity response
		
		given: "A client message"
			Message message = new Message(content: "FAKE MESSAGE", sentAt:"10:10:10" )
				
		when: "The cliente send a new Message to a Specific User"
			response = chatMessageController.handlePrivateMessage(message, "FAKE_TARGET", new FakePrincipal("FAKE_SENDER"))
		
		then: "Message is send to the Receiver User"
			1 * messageBuilder.privateMessage(message, "FAKE_TARGET", "FAKE_SENDER") >> privMessage
			1 * template.convertAndSend("/queue/messages-FAKE_TARGET", privMessage)
			HttpStatus.CREATED == response.statusCode

	}
	
}
