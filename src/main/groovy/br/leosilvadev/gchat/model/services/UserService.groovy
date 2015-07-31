package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

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
	
}
