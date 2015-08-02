package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus

import spock.lang.Specification
import br.leosilvadev.gchat.auth.Authenticator
import br.leosilvadev.gchat.exceptions.AuthTokenGenerationException
import br.leosilvadev.gchat.mail.dto.AuthenticationRequest

class AuthenticationControllerSpec extends Specification {

	AuthenticationController controller
	Authenticator authenticator
	def onSuccess
	def onFailure
	
	def setup(){
		authenticator = Mock(Authenticator)
		
		controller = new AuthenticationController(authenticator: authenticator)
		onSuccess = controller.authenticateOnSuccess
		onFailure = controller.authenticateOnFailure
	}
	
	def "Should respond an OK response when the user authenticates correctly"(){
		def authenticated = new AuthenticationRequest("Leonardo", "123456").authenticated()
		def response
		
		given: "A valid authentication"
			def authentication = new AuthenticationRequest("Leonardo", "123456")
		
		when: "The user try to authenticate"
			response = controller.authenticate(authentication)
		
		then: "When the user is authenticated"
			1 * authenticator.authenticate("Leonardo", "123456", onSuccess, onFailure) >> { onSuccess(authenticated) }
		
		and: "The client must receive an OK response"
			response.statusCode == HttpStatus.OK
			response.body.token
	}
	
	def "Should respond UNAUTHORIZED response when the user authentication fails"(){
		def notAuthenticated = new AuthenticationRequest("Leonardo", "123456")
		def response
		
		given: "A valid authentication"
			def authentication = new AuthenticationRequest("Leonardo", "123456")
		
		when: "The user try to authenticate"
			response = controller.authenticate(authentication)
		
		then: "When the user is authenticated"
			1 * authenticator.authenticate("Leonardo", "123456", onSuccess, onFailure) >> { onFailure(new AuthTokenGenerationException("Error")) }
		
		and: "The client must receive an OK response"
			response.statusCode == HttpStatus.UNAUTHORIZED
			response.body.message == "Error"
	}
	
}
