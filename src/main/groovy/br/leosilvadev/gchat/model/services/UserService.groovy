package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import br.leosilvadev.gchat.mail.dto.Authentication
import br.leosilvadev.gchat.repositories.UserRepository
import br.leosilvadev.gchat.security.UserSecurity

@Service
class UserService {
	
	@Autowired UserRepository repository
	
	def currentUser(){
		UserSecurity user = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		repository.findOneByEmail user.getUsername()
	}
	
	def currentUser(principal){
		repository.findOneByEmail principal.getUsername()
	}
	
	def authenticate(username, password, onSuccess=null, onFailure=null){
		def user = repository.findOneByEmailAndPassword(username, password)
		def authentication = new Authentication(username, password)
		
		if( user && onSuccess) onSuccess(authentication.authenticated())
		if(!user && onFailure) onFailure(authentication)
	}
	
}
