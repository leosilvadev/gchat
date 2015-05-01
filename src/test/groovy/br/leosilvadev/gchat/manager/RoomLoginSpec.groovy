package br.leosilvadev.gchat.manager

import spock.lang.Specification
import br.leosilvadev.gchat.exceptions.RoomNotFoundException;
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.model.dto.ChatRoom
import br.leosilvadev.gchat.security.UserSecurity

class RoomLoginSpec extends Specification {

	RoomsManager roomsManager
	
	def setup(){
		roomsManager = new RoomsManager()
	}
	
	def "Should login at a specific Room"(){
		given: "A existent Room and logged User"
			def room = new ChatRoom(code: "1234", name: "Room 1234")
			roomsManager.newRoom(room)
			def user = new UserSecurity(new User(name: "Fake User", email: "fake@user.com"), "ANYCODE")
			
		when: "The logged User try to login at the Room"
			roomsManager.login(user).to(room.code)
			
		then: "User should enter in the Room"
			room.users.size() == 1
	}
	
	def "Should not login if the Room does not exist"(){
		given: "An non existent Room"
			def room = new ChatRoom(code: "1234", name: "Room 1234")
			def user = new UserSecurity(new User(name: "Fake User", email: "fake@user.com"), "ANYCODE")
			
		when: "The logged User try to login at the Room"
			roomsManager.login(user).to(room.code)
		
		then: "The user must got an Error"
			thrown(RoomNotFoundException)
	}
	
}
