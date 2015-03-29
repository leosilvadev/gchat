package br.leosilvadev.gchat.controller

import javax.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import br.leosilvadev.gchat.manager.RoleManager
import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.model.dto.ChatUser
import br.leosilvadev.gchat.repositories.UserRepository
import br.leosilvadev.gchat.validators.ChatUserValidator

@Controller
@RequestMapping("/users")
class UserController {

	@Autowired UserRepository userRepository
	@Autowired RoleManager roleManager
	@Autowired BCryptPasswordEncoder encoder
	
	@RequestMapping(method=RequestMethod.POST)
	def register(@Valid @RequestBody ChatUser chatUser, BindingResult bindingResult){
		if ( bindingResult.hasErrors() ) return new ResponseEntity(HttpStatus.BAD_REQUEST)
		
		def user = 
			new User(
				email: chatUser.email, 
				name: chatUser.name, 
				password: encoder.encode(chatUser.password), 
				roles: [roleManager.userRole()]
			)
		
		userRepository.save(user)
		new ResponseEntity(HttpStatus.CREATED)
	}
	
	@InitBinder
    def void initBinder(WebDataBinder binder) {
        binder.setValidator(new ChatUserValidator());
    }
	
}
