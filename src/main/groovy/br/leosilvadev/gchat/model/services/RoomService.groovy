package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.leosilvadev.gchat.model.domain.Room
import br.leosilvadev.gchat.model.dto.ChatRoom
import br.leosilvadev.gchat.repositories.RoomRepository

@Service
@Transactional
class RoomService {
	
	@Autowired UserService userService
	@Autowired RoomRepository repository
	
	def register(chatRoom){
		def room = new Room().withName(chatRoom.name).createdBy(userService.currentUser())
		repository.save room
	}
	
	def enter(roomCode, principal){
		def room = repository.findOne roomCode
		room.addUser userService.currentUser(principal)
	}
	
	def logout(roomCode, principal){
		def room = repository.findOne roomCode
		room.removeUser userService.currentUser(principal)
	}
	
	def allWithName(name){
		repository.findAllByNameContaining name
	}

}
