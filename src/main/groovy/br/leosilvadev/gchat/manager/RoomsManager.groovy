package br.leosilvadev.gchat.manager

import org.springframework.stereotype.Component

import br.leosilvadev.gchat.exceptions.RoomNotFoundException
import br.leosilvadev.gchat.exceptions.RoomValidationException
import br.leosilvadev.gchat.model.dto.Room
import br.leosilvadev.gchat.security.UserSecurity

@Component
class RoomsManager {

	private List rooms = []
	
	def newRoom(Room room){
		if ( room.name ) {
			rooms.add(room)
		}
		else throw new RoomValidationException()
	}
	
	def rooms(roomName) {
		if (roomName) {
			this.rooms.findAll { room ->
				roomName && room.name.contains(roomName)
			}
			
		} else {
			[]
			
		}
	}
	
	def doInRoom(codeRoom, callback){
		boolean found = false
		rooms.each { 
			if ( it.code.equals(codeRoom) ) callback(it); found = true;
		}
		if(!found) throw new RoomNotFoundException()
	}
	
	def login(user){
		new LoginRequest(user, this)
	}
	
	static class LoginRequest {
		private UserSecurity user
		private RoomsManager roomsManager
		
		LoginRequest(user, roomsManager){
			this.user = user
			this.roomsManager = roomsManager
		}
		
		def to(codeRoom){
			roomsManager.doInRoom(codeRoom){
				it.addUser(user)
			}
		}
	}
	
}
