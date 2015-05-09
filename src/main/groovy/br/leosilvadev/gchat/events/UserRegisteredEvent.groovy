package br.leosilvadev.gchat.events

import org.springframework.context.ApplicationEvent;

class UserRegisteredEvent extends ApplicationEvent {

	UserRegisteredEvent(user){
		super(user)
	}
	
}
