package br.leosilvadev.gchat.model.user

import groovy.transform.EqualsAndHashCode;

import org.springframework.context.ApplicationEvent


@EqualsAndHashCode(includes=["resource"])
class ChatUserRegisteringEvent extends ApplicationEvent {

	ChatUserRegisteringEvent(chatUser){
		super(chatUser)
	}
	
}
