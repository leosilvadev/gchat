package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import br.leosilvadev.gchat.exceptions.AuthenticationException
import br.leosilvadev.gchat.mail.dto.Authentication
import br.leosilvadev.gchat.repositories.UserRepository
import br.leosilvadev.gchat.security.UserSecurity

@Service
class UserService {
	
	@Autowired UserRepository repository
	@Autowired BCryptPasswordEncoder encoder
	
	def authenticate(username, password, onSuccess, onFailure){
		def user = repository.findOneByEmailAndPassword(username, encoder.encode(password))
		def authentication = new Authentication(username, password)
		user ? onSuccess(authentication.authenticated()) : onFailure(authentication)
	}
	
	def currentUser(){
		UserSecurity user = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		repository.findOneByEmail user.getUsername()
	}
	
	def currentUser(principal){
		repository.findOneByEmail principal.getUsername()
	}
	
}
