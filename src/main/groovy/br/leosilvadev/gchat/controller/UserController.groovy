package br.leosilvadev.gchat.controller

import javax.validation.Valid
import javax.validation.Validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.model.services.UserService
import br.leosilvadev.gchat.validators.ChatUserValidator

@Controller
@RequestMapping("/users")
class UserController {

	@Autowired UserService userService
	
	@Autowired Validator validator
	@Autowired ChatUserValidator chatUserValidator
	
	@RequestMapping(method=RequestMethod.POST)
	def register(@Valid @RequestBody ChatUser chatUser, BindingResult bindingResult){
		if ( bindingResult.hasErrors() ) return new ResponseEntity(HttpStatus.BAD_REQUEST)
		def user = userService.register(chatUser)
		new ResponseEntity(user, HttpStatus.CREATED)
	}
	
	@InitBinder
    void initBinder(WebDataBinder binder) {
		binder.addValidators(chatUserValidator, validator)
    }
	
}
