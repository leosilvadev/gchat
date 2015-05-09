package br.leosilvadev.gchat.model.user

import org.springframework.context.ApplicationEvent


class ChatUserRegisteringEvent extends ApplicationEvent {

	ChatUserRegisteringEvent(chatUser){
		super(chatUser)
	}
	
}
