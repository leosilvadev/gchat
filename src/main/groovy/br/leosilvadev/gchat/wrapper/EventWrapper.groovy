package br.leosilvadev.gchat.wrapper

import br.leosilvadev.gchat.security.UserSecurity;

class EventWrapper {
	
	def event
	
	EventWrapper(event){ this.event = event }

	UserSecurity sender(){
		event.getMessage().getHeaders()?.get("simpUser")?.getPrincipal()
	}
	
	String destination(){
		event.getMessage().getHeaders()?.get("simpDestination")
	}
	
	boolean isRoomsSubscriber(){
		destination().split("/")[2].split("-")[0].equals("rooms")
	}
	
	String roomCode(){
		event.getMessage().getHeaders()?.get('nativeHeaders')?.get("roomCode")[0]
	}
	
}
