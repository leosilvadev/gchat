package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.leosilvadev.gchat.model.domain.Room
import br.leosilvadev.gchat.notifiers.UserLoginNotifier
import br.leosilvadev.gchat.notifiers.UserLogoutNotifier
import br.leosilvadev.gchat.repositories.RoomRepository

@Service
@Transactional
class RoomService {
	
	@Autowired UserService 		  userService
	@Autowired RoomRepository 	  repository
	@Autowired UserLoginNotifier  userLoginNotifier
	@Autowired UserLogoutNotifier userLogoutNotifier
	
	def register(chatRoom){
		def room = new Room().withName(chatRoom.name).createdBy(userService.currentUser())
		repository.save room
		chatRoom.code = room.code
		chatRoom.createdAt = room.createdAt
	}
	
	def enter(roomCode, principal){
		def room = repository.findOne roomCode
		def currentUser = userService.currentUser principal
		
		if ( !room.users.contains(currentUser) ){
			room.addUser(currentUser)
			userLoginNotifier.to(roomCode).by(currentUser).send()
		}
	}
	
	def logout(roomCode, principal){
		def room = repository.findOne roomCode
		def currentUser = userService.currentUser principal

		room.removeUser userService.currentUser(principal)
		userLogoutNotifier.to(roomCode).by(currentUser).send()
	}
	
	def allWithName(name){
		repository.findAllByNameContaining name
	}
	
	def listUsersFrom(roomCode){
		def room = repository.findOne roomCode
		def users = room.users
		users
	}

}
