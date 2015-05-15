package br.leosilvadev.gchat.events

import org.springframework.context.ApplicationEvent

class MailMessageReadEvent extends ApplicationEvent {
	
	MailMessageReadEvent(mailMessage){
		super(mailMessage)
	}
	
}
