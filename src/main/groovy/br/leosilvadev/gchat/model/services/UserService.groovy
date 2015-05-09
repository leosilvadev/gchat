package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.leosilvadev.gchat.builder.UserBuilder
import br.leosilvadev.gchat.events.UserRegisteredEvent
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.repositories.UserRepository
import br.leosilvadev.gchat.security.UserSecurity

@Service
@Transactional
class UserService {
	
	@Autowired UserRepository repository
	@Autowired UserBuilder userBuilder
	@Autowired ApplicationContext applicationContext
	
	def register(ChatUser chatUser){
		def user = userBuilder.build(chatUser)
								.onSuccess {println "User ${it.name} was saved succesfully!"}
								.onError {println "Error saving user ${it.name}"}
								.saveOn repository
			
		applicationContext.publishEvent(new UserRegisteredEvent(user))
	}
	
	def currentUser(){
		UserSecurity user = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		repository.findOneByEmail user.getUsername()
	}
	
	def currentUser(principal){
		repository.findOneByEmail principal.getUsername()
	}
	
}
