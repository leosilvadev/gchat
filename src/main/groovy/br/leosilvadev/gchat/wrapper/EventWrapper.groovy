package br.leosilvadev.gchat.wrapper

import br.leosilvadev.gchat.security.UserSecurity;

class EventWrapper {
	
	def event
	
	EventWrapper(event){ this.event = event }

	UserSecurity getSender(){
		event.getMessage().getHeaders().get("simpUser").getPrincipal()
	}
	
	String getDestination(){
		event.getMessage().getHeaders().get("simpDestination")
	}
	
}
