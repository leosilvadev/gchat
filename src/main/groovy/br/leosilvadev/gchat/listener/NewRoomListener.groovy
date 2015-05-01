package br.leosilvadev.gchat.listener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.events.NewRoomEvent
import br.leosilvadev.gchat.model.services.RoomService

@Component
class NewRoomListener implements ApplicationListener<NewRoomEvent> {
	
	@Autowired RoomService roomService
	@Autowired SimpMessagingTemplate template
	
	@Override
	void onApplicationEvent(NewRoomEvent event) {
		roomService.register event.source
		template.convertAndSend("/topic/rooms", event.source)
	}

}
