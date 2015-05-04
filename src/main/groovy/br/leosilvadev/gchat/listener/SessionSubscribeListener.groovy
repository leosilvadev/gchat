package br.leosilvadev.gchat.listener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionSubscribeEvent

import br.leosilvadev.gchat.model.services.RoomService
import br.leosilvadev.gchat.model.services.UserService
import br.leosilvadev.gchat.wrapper.EventWrapper

@Component
class SessionSubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

	@Autowired UserService userService
	@Autowired RoomService roomService
	
	def notifiers
	
	@Override
	void onApplicationEvent(SessionSubscribeEvent event) {
		def wrapper = new EventWrapper(event)
		def principal = wrapper.sender()
		def destination = wrapper.destination()
		
		wrapper.isRoomsSubscriber() && roomService.enter(wrapper.roomCode(), principal)		
	}

}
