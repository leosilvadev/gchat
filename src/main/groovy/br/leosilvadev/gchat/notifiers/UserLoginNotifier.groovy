package br.leosilvadev.gchat.notifiers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.utils.ChatConstants

@Component
class UserLoginNotifier {
	
	@Autowired MessageBuilder builder
	@Autowired SimpMessagingTemplate template
	
	LoginNotifierRequest to(roomCode){
		new LoginNotifierRequest(roomCode: roomCode, template: template, builder: builder)
	}
	
	static class LoginNotifierRequest {
		def roomCode
		def messageContent
		def chatUser
		
		def template
		def builder
		
		LoginNotifierRequest by(user){
			messageContent = "Let's welcome <b>${user.name}</b>".toString()
			chatUser = new ChatUser(id: user.id, name: user.name, email: user.email)
			this
		}
		
		void send(){
			def topic = "/topic/rooms-${roomCode}".toString()
			template.convertAndSend(topic, builder.newUser(messageContent, chatUser, ChatConstants.NEW_USER))
		}
	}

}
