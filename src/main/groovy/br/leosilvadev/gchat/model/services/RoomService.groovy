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
	
	def register(ChatRoom chatRoom){
		def room = new Room().withName(chatRoom.name).createdBy(userService.currentUser())
		repository.save room
	}

}
