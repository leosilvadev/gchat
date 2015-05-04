package br.leosilvadev.gchat.listener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent

import br.leosilvadev.gchat.model.services.RoomService
import br.leosilvadev.gchat.model.services.UserService
import br.leosilvadev.gchat.wrapper.EventWrapper

@Component
class SessionUnsubscribeListener implements ApplicationListener<SessionUnsubscribeEvent> {

	@Autowired UserService userService
	@Autowired RoomService roomService
	
	def notifiers
	
	@Override
	void onApplicationEvent(SessionUnsubscribeEvent event) {
		def wrapper = new EventWrapper(event)
		def principal = wrapper.sender()
		
		def roomCode = wrapper.roomCode()
		roomCode && roomService.logout(roomCode, principal)
		
	}

}
