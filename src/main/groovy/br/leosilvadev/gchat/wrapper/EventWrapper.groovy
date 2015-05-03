package br.leosilvadev.gchat.wrapper

import br.leosilvadev.gchat.security.UserSecurity;

class EventWrapper {
	
	def event
	
	EventWrapper(event){ this.event = event }

	UserSecurity sender(){
		event.message.headers?.simpUser?.principal
	}
	
	String destination(){
		event.message.headers?.simpDestination
	}
	
	boolean isRoomsSubscriber(){
		destination().split("/")[2].split("-")[0].equals("rooms")
	}
	
	String roomCode(){
		def nativeHeaders = event.message.headers?.nativeHeaders
		def roomCode = nativeHeaders?.get("roomCode")[0]
		roomCode
	}
	
}
