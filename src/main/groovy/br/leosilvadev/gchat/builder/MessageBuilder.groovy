package br.leosilvadev.gchat.builder

import org.springframework.stereotype.Component

import br.leosilvadev.gchat.model.dto.PrivateMessage
import br.leosilvadev.gchat.model.dto.PublicMessage

@Component
class MessageBuilder {

	def publicMessage(message, room, from) {
		new PublicMessage(room: room, from: from, content: message.content)
	}
	
	def privateMessage(message, to, from) {
		new PrivateMessage(to: to, from: from, content: message.content)
	}
	
}
