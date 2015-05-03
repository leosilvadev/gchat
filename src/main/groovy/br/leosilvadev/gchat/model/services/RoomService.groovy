package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.domain.Room
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.repositories.RoomRepository
import br.leosilvadev.gchat.utils.ChatConstants

@Service
@Transactional
class RoomService {
	
	@Autowired MessageBuilder builder
	@Autowired SimpMessagingTemplate template
	@Autowired UserService userService
	@Autowired RoomRepository repository
	
	def register(chatRoom){
		def room = new Room().withName(chatRoom.name).createdBy(userService.currentUser())
		repository.save room
		chatRoom.code = room.code
		chatRoom.createdAt = room.createdAt
	}
	
	def enter(roomCode, principal){
		def room = repository.findOne roomCode
		def currentUser = userService.currentUser(principal)
		
		if ( !room.users.contains(currentUser) ){
			room.addUser(currentUser)
			
			def topic = "/topic/rooms-"+room.code
			def messageContent = "Let's welcome <b>" + currentUser.name + "</b>"
			
			def chatUser = new ChatUser(name: currentUser.name, email: currentUser.email)
			
			template.convertAndSend(topic, builder.newUser(messageContent, chatUser, ChatConstants.NEW_USER))
		}
	}
	
	def logout(roomCode, principal){
		def room = repository.findOne roomCode
		room.removeUser userService.currentUser(principal)
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
