package br.leosilvadev.gchat.controller

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import br.leosilvadev.gchat.auth.Authenticator
import br.leosilvadev.gchat.mail.dto.AuthenticationRequest

@RestController
@RequestMapping("/auth")
@Slf4j
class AuthenticationController extends GController {
	
	@Autowired Authenticator authenticator
	
	def authenticateOnSuccess = { authenticated ->
		ok([token: authenticated.token])
	}
	
	def authenticateOnFailure = { ex ->
		unauthorized([message: ex.getMessage()])
	}
	
	@RequestMapping(method=RequestMethod.POST)
	def authenticate(@RequestBody AuthenticationRequest authentication){
		authenticator.authenticate(
			authentication.username, 
			authentication.password,
			authenticateOnSuccess,
			authenticateOnFailure
		)
	}
	
}
