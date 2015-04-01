package br.leosilvadev.gchat.events

import java.time.LocalDate

import org.springframework.context.ApplicationEvent

import br.leosilvadev.gchat.model.dto.Room

class NewRoomEvent extends ApplicationEvent {

	NewRoomEvent(room){
		super(room)
	}
	
}
