package br.leosilvadev.gchat.controller

import java.security.Principal
import java.util.concurrent.ConcurrentHashMap

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.dto.Message

@Controller
@RequestMapping("/chat")
class ChatMessageController {
	
	@Autowired SimpMessagingTemplate template
	@Autowired MessageBuilder builder
	
	public static ConcurrentHashMap<String, String> users;
	
	@PostConstruct
	def init(){
		users = new ConcurrentHashMap<>()
	}
	
	@RequestMapping(value="/{room}/messages", method=RequestMethod.POST)
    def handlePublicMessage(
		@RequestBody Message message, 
		@PathVariable String room,
		Principal loggedUser) {
		
		template.convertAndSend("/topic/rooms-"+room, builder.publicMessage(message, room, loggedUser.getName()))
		
		new ResponseEntity(HttpStatus.CREATED)
    }
		
	@RequestMapping(value="/messages/{user}", method=RequestMethod.POST)
	def handlePrivateMessage(
		@RequestBody Message message, 
		@PathVariable String user,
		Principal loggedUser) throws Exception {
		
		template.convertAndSend("/queue/messages-"+user, builder.privateMessage(message, user, loggedUser.getName()))
		
		new ResponseEntity(HttpStatus.CREATED)
	}
	
}
