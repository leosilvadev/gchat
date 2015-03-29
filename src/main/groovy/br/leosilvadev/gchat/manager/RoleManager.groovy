package br.leosilvadev.gchat.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.repositories.RoleRepository

@Component
class RoleManager {
	
	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";
	
	@Autowired RoleRepository repository
	
	def userRole(){
		repository.findOneByName(USER_ROLE)
	}
	
	def adminRole(){
		repository.findOneByName(USER_ROLE)
	}
	
}
