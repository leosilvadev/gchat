package br.leosilvadev.gchat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import br.leosilvadev.gchat.mail.dto.Authentication
import br.leosilvadev.gchat.model.services.UserService

@RestController
@RequestMapping("/auth")
class AuthenticationController extends GController {
	
	@Autowired UserService userService
	
	def authenticateOnSuccess = { authenticated ->
		ok(authenticated)
	}
	
	def authenticateOnFailure = { authentication ->
		unauthorized(authentication)
	}
	
	@RequestMapping(method=RequestMethod.POST)
	def authenticate(@RequestBody Authentication authentication){
		userService.authenticate(
			authentication.username, 
			authentication.password,
			authenticateOnSuccess,
			authenticateOnFailure
		)
	}
	
}
