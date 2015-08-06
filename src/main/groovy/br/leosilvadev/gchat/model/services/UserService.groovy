package br.leosilvadev.gchat.model.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.repositories.UserRepository

@Service
class UserService {
	
	@Autowired UserRepository repository
	
	def currentUser(){
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		repository.findOneByEmail user.getEmail()
	}
	
	def currentUser(principal){
		repository.findOneByEmail principal.getEmail()
	}
	
	def findByUsernameAndPassword(username, password){
		repository.findOneByEmailAndPassword(username, password.hash())
	}
	
}
