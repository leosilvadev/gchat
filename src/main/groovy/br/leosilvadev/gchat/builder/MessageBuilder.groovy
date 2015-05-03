package br.leosilvadev.gchat.builder

import org.springframework.stereotype.Component

import br.leosilvadev.gchat.model.dto.NewUserMessage
import br.leosilvadev.gchat.model.dto.PrivateMessage
import br.leosilvadev.gchat.model.dto.PublicMessage

@Component
class MessageBuilder {
	
	def publicSystemMessage(message, room) {
		new PublicMessage(room: room, content: message.content)
	}
	
	def newUser(messageContent, user, from){
		new NewUserMessage(user: user, from: from, content: messageContent)
	}
	
	def publicMessage(messageContent, room, from) {
		new PublicMessage(room: room, from: from, content: messageContent)
	}
	
	def privateMessage(message, to, from) {
		new PrivateMessage(to: to, from: from, content: message.content)
	}
	
}
