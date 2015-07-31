package br.leosilvadev.gchat.builder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.encoding.ShaPasswordEncoder
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.manager.RoleManager
import br.leosilvadev.gchat.model.domain.User

@Component
class UserBuilder {
	
	@Autowired RoleManager roleManager
	@Autowired ShaPasswordEncoder encoder

	def build(chatUser){
		def user = new User(
			email: chatUser.email,
			name: chatUser.name,
			password: encoder.encodePassword(chatUser.password, chatUser.password),
			locked: Boolean.FALSE, 
			roles: [roleManager.userRole()]
		)
		new UserRegistration(user)
	}
	
	static class UserRegistration {
		private User user
		private Closure successCallback
		private Closure errorCallback
		
		UserRegistration(user){ 
			this.user = user
		}
		
		def onSuccess(succesCallback) {
			this.successCallback = succesCallback
			this
		}
		
		def onError(errorCallback) {
			this.errorCallback = errorCallback
			this
		}
		
		def saveOn(repository){
			try {
				repository.save(this.user)
				if(successCallback) successCallback(user)
				
			} catch(ex) {
				if(errorCallback) errorCallback(user, ex)
				
			}
			this.user
		}
	}
	
}
