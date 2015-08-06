package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult

import spock.lang.Specification
import br.leosilvadev.gchat.events.Publisher
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.model.user.ChatUserRegisteringEvent
import br.leosilvadev.gchat.users.controller.v1.UserController;

class UserControllerSpec extends Specification {
	
	def controller
	def publisher
	
	def setup(){
		publisher = Mock(Publisher)
		
		controller = new UserController()
		controller.publisher = publisher
	}
	
	def "Should register a new User"(){
		BindingResult bindingResult = Mock(BindingResult)
		ResponseEntity response
		
		given: "A new User"
			def chatUser = new ChatUser(name: "fake", email: "fake@fake.com", password: "abc", passwordConfirmation: "abc")
			
		when: "The User register himself in the application"
			response = controller.register(chatUser, bindingResult)
		
		then: "The User must be registered"
			1 * publisher.publish(new ChatUserRegisteringEvent(chatUser))
			response.getStatusCode() == HttpStatus.CREATED
	}
	
	
	def "Should not register a User with a validation error"(){
		BindingResult bindingResult = Mock(BindingResult)
		ResponseEntity response
		
		given: "A new User"
			def chatUser = new ChatUser(name: "fake", email: "fake@fake.com", password: "abc")
			
		when: "The User try to register himself"
			response = controller.register(chatUser, bindingResult)
			
		then: "The user must got an validation error"
			1 * bindingResult.hasErrors() >> true
			response.getStatusCode() == HttpStatus.BAD_REQUEST
			
	}

}
