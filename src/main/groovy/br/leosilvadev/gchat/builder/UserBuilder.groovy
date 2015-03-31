package br.leosilvadev.gchat.builder

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.manager.RoleManager;
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.repositories.UserRepository;

@Component
class UserBuilder {
	
	@Autowired RoleManager roleManager
	@Autowired BCryptPasswordEncoder encoder

	def build(chatUser){
		def user = new User(
			email: chatUser.email,
			name: chatUser.name,
			password: encoder.encode(chatUser.password),
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
		
		def save(repository){
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
