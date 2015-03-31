package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import br.leosilvadev.gchat.builder.UserBuilder
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.repositories.UserRepository

@Service
class UserService {
	
	@Autowired UserRepository userRepository
	@Autowired UserBuilder userBuilder
	
	def register(ChatUser chatUser){
		userBuilder
			.build(chatUser)
			.onSuccess({println "User ${it.name} was saved succesfully!"})
			.onError({println "Error saving user ${it.name}"})
			.save(userRepository)
	}
	
}
