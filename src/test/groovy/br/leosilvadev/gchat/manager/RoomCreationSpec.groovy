package br.leosilvadev.gchat.manager

import spock.lang.Specification
import br.leosilvadev.gchat.exceptions.RoomValidationException;
import br.leosilvadev.gchat.model.dto.Room

class RoomCreationSpec extends Specification {

	def roomsManager
	
	def setup(){
		roomsManager = new RoomsManager()
	}
	
	def "Should create Rooms"(){
		given: "Some rooms"
			def room1234 = new Room(name: "Room 1234")
			def room4567 = new Room(name: "Room 4567")
		
		when: "The user create some rooms"
			roomsManager.newRoom(room1234)
			roomsManager.newRoom(room4567)
			
		then: "The room should must be visible to the clients"
			roomsManager.rooms().size() == 2
	}
	
	def "Should not create Room without a name"(){
		given: "A Room without name"
			def room = new Room()
			
		when: "The user try to create it"
			roomsManager.newRoom(room)
			
		then: "The room is not saved and the user got an error"
			thrown(RoomValidationException)
	}
	
}
