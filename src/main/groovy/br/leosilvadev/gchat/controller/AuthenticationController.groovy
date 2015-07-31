package br.leosilvadev.gchat.controller

import groovy.util.logging.Log;
import groovy.util.logging.Log4j2;
import groovy.util.logging.Slf4j;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import br.leosilvadev.gchat.auth.Authenticator;
import br.leosilvadev.gchat.mail.dto.Authentication
import br.leosilvadev.gchat.model.services.UserService

@RestController
@RequestMapping("/auth")
@Slf4j
class AuthenticationController extends GController {
	
	@Autowired Authenticator authenticator
	
	def authenticateOnSuccess = { authenticated ->
		ok([token: authenticated.token])
	}
	
	def authenticateOnFailure = { ex ->
		log.error(ex.getMessage(), ex)
		unauthorized([message: ex.getMessage()])
	}
	
	@RequestMapping(method=RequestMethod.POST)
	def authenticate(@RequestBody Authentication authentication){
		authenticator.authenticate(
			authentication.username, 
			authentication.password,
			authenticateOnSuccess,
			authenticateOnFailure
		)
	}
	
}
