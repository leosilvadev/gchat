package br.leosilvadev.gchat.builder

import org.springframework.stereotype.Component

import br.leosilvadev.gchat.model.dto.PublicMessage

@Component
class MessageBuilder {

	def publicMessage(message, room) {
		new PublicMessage(room: room, from: message.from, content: message.content)
	}
	
}
