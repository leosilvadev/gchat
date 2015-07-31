package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus

import spock.lang.Specification
import br.leosilvadev.gchat.mail.dto.Authentication
import br.leosilvadev.gchat.mail.dto.Authentication.Authenticated
import br.leosilvadev.gchat.model.services.UserService

class AuthenticationControllerSpec extends Specification {

	AuthenticationController controller
	UserService userService
	def onSuccess
	def onFailure
	
	def setup(){
		userService = Mock(UserService)
		
		controller = new AuthenticationController(userService: userService)
		onSuccess = controller.authenticateOnSuccess
		onFailure = controller.authenticateOnFailure
	}
	
	def "Should respond an OK response when the user authenticates correctly"(){
		def authenticated = new Authentication("Leonardo", "123456").authenticated()
		def response
		
		given: "A valid authentication"
			def authentication = new Authentication("Leonardo", "123456")
		
		when: "The user try to authenticate"
			response = controller.authenticate(authentication)
		
		then: "When the user is authenticated"
			1 * userService.authenticate("Leonardo", "123456", onSuccess, onFailure) >> { onSuccess(authenticated) }
		
		and: "The client must receive an OK response"
			response.statusCode == HttpStatus.OK
			response.body instanceof Authenticated
			response.body.token
	}
	
	def "Should respond UNAUTHORIZED response when the user authentication fails"(){
		def notAuthenticated = new Authentication("Leonardo", "123456")
		def response
		
		given: "A valid authentication"
			def authentication = new Authentication("Leonardo", "123456")
		
		when: "The user try to authenticate"
			response = controller.authenticate(authentication)
		
		then: "When the user is authenticated"
			1 * userService.authenticate("Leonardo", "123456", onSuccess, onFailure) >> { onFailure(authentication) }
		
		and: "The client must receive an OK response"
			response.statusCode == HttpStatus.UNAUTHORIZED
			response.body.message == "Invalid access data"
	}
	
}
