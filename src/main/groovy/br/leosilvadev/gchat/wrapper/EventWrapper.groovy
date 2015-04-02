package br.leosilvadev.gchat.wrapper

import br.leosilvadev.gchat.security.UserSecurity;

class EventWrapper {
	
	def event
	
	EventWrapper(event){ this.event = event }

	UserSecurity sender(){
		event.getMessage().getHeaders().get("simpUser").getPrincipal()
	}
	
	String destination(){
		event.getMessage().getHeaders().get("simpDestination")
	}
	
	String loggedRoom(destination){
		if ( destination.contains("-") ){
			def brokenDest = destination.split("-")
			if (brokenDest[0]?.equals("/topic/rooms")) return brokenDest[1]
		}
		return null
	}
	
}
