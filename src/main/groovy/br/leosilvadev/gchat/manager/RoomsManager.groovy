package br.leosilvadev.gchat.manager

import javax.annotation.PostConstruct

import org.springframework.stereotype.Component

import br.leosilvadev.gchat.model.dto.Room

@Component
class RoomsManager {

	List rooms
	
	@PostConstruct
	def init(){
		rooms = [
			new Room(code:"12345", name:"Room 1"),
			new Room(code:"23456", name:"Room 2"),
			new Room(code:"34567", name:"Room 3"),
			new Room(code:"45678", name:"Room 4"),
		]
	}
	
}
