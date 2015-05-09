package br.leosilvadev.gchat.controller

import javax.validation.Valid
import javax.validation.Validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController;

import br.leosilvadev.gchat.events.Publisher
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.model.user.ChatUserRegisteringEvent
import br.leosilvadev.gchat.validators.ChatUserValidator


@RestController
@RequestMapping("/users")
class UserController extends GController {

	@Autowired Publisher publisher
	
	@Autowired Validator validator
	@Autowired ChatUserValidator chatUserValidator
	
	@RequestMapping(method=RequestMethod.POST)
	def register(@Valid @RequestBody ChatUser chatUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()) return badRequest()
		
		def user = publisher.publish(new ChatUserRegisteringEvent(chatUser))
		
		created(user)
	}
	
	@InitBinder
    void initBinder(WebDataBinder binder) {
		binder.addValidators(chatUserValidator, validator)
    }
	
}
