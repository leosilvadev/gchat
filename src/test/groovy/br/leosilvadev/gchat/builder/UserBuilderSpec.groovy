package br.leosilvadev.gchat.builder

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import spock.lang.Specification
import br.leosilvadev.gchat.manager.RoleManager
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.repositories.UserRepository

class UserBuilderSpec extends Specification {

	UserRepository repository
	
	UserBuilder userBuilder
	RoleManager roleManager
	BCryptPasswordEncoder encoder
	
	Closure callback
	
	def setup(){
		repository = Mock(UserRepository)
		roleManager = Mock(RoleManager)
		encoder = Mock(BCryptPasswordEncoder)
		
		callback = Mock(Closure)
		
		userBuilder = new UserBuilder(roleManager: roleManager, encoder: encoder)
	}
	
	def "Should save a user with callback"(){
		def generatedUser = new User(email:"user@user.com")
		given: "A new user to be saved"
			def chatUser = new ChatUser(name: "User", email: "user@user.com", password: "user")
		
		when: "The user is build and save passing a success callback"
			userBuilder.build(chatUser).onSuccess(callback).saveOn(repository)
			
		then: "User have to be saved and the callback must execute"
			1 * callback.call(generatedUser)
			1 * repository.save(generatedUser)
			1 * encoder.encode("user")
	}
	
	def "Should not save a user and execute a callback"(){
		def ex = new RuntimeException()
		def generatedUser = new User(email:"user@user.com")
		
		given: "A new user to be saved"
			def chatUser = new ChatUser(name: "User", email: "user@user.com", password: "user")
		
		when: "The user is build passing an error callback"
			userBuilder.build(chatUser).onError(callback).saveOn(repository)
			
		then: "If the user has a problemn, error callback must execute"
			1 * encoder.encode("user")
			1 * repository.save(generatedUser) >> { throw ex }
			1 * callback.call(generatedUser, ex)
			
	}
	
}
