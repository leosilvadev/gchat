package br.leosilvadev.gchat.model.services

import spock.lang.Specification
import br.leosilvadev.gchat.model.domain.Room
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.notifiers.UserLoginNotifier
import br.leosilvadev.gchat.notifiers.UserLoginNotifier.LoginNotifierRequest
import br.leosilvadev.gchat.repositories.RoomRepository

class RoomServiceSpec extends Specification {

	def service
	def repository
	def userService
	def userLoginNotifier
	
	def setup(){
		repository 	= Mock(RoomRepository)
		userService = Mock(UserService)
		userLoginNotifier	= Mock(UserLoginNotifier)
		
		service 	= new RoomService(repository: repository, userService: userService, userLoginNotifier: userLoginNotifier)
	}
	
	def "Should enter in a Room"(){
		def notifierRequest = Mock(LoginNotifierRequest)
		def user = new User()
		def room = new Room()
		
		given: "A user and Room"
			def principal = new Object()
		
		when: "The user try to enter in the room"
			service.enter "1234", principal
			
		then: "The user must be inside the room"
			1 * repository.findOne("1234") 			>> room
			1 * userService.currentUser(principal) 	>> user
			1 * userLoginNotifier.to("1234")		>> notifierRequest
			1 * notifierRequest.by(user)			>> notifierRequest
			room.users.size() == 1
		
	}
	
}
