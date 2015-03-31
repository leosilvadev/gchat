package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult

import spock.lang.Specification
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.model.services.UserService

class UserRegistrationSpec extends Specification {
	
	def controller
	def userService
	
	def setup(){
		userService = Mock(UserService)
		
		controller = new UserController()
		controller.userService = userService
	}
	
	def "Should register a new User"(){
		def bindingResult = Mock(BindingResult)
		ResponseEntity response
		
		given: "A new User"
			def user = new ChatUser(name: "fake", email: "fake@fake.com", password: "abc", passwordConfirmation: "abc")
			
		when: "The User register himself in the application"
			response = controller.register(user, bindingResult)
		
		then: "The User must be registered"
			1 * userService.register(user)
			response.getStatusCode() == HttpStatus.CREATED
	}
	
	
	def "Should not register a User with a validation error"(){
		def bindingResult = Mock(BindingResult)
		ResponseEntity response
		
		given: "A new User"
			def user = new ChatUser(name: "fake", email: "fake@fake.com", password: "abc")
			
		when: "The User try to register himself"
			response = controller.register(user, bindingResult)
			
		then: "The user must got an validation error"
			1 * bindingResult.hasErrors() >> true
			response.getStatusCode() == HttpStatus.BAD_REQUEST
			
	}
	

}
