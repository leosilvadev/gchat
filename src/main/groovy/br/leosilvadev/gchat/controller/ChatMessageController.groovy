package br.leosilvadev.gchat.controller

import java.security.Principal;
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
import br.leosilvadev.gchat.model.dto.PrivateMessage

@Controller
@RequestMapping("/chat")
class ChatMessageController {
	
	@Autowired SimpMessagingTemplate template;
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
		
		template.convertAndSend("/topic/rooms-"+room, builder.publicMessage(message, room, loggedUser.getPrincipal().getUsername()))
		
		new ResponseEntity(HttpStatus.OK)
    }
		
	@RequestMapping(value="/messages/{user}", method=RequestMethod.POST)
	def handlePrivateMessage(
		@RequestBody Message message, 
		@PathVariable String user,
		Principal loggedUser) throws Exception {
		
		def privateMessage = builder.privateMessage(to: user, from: loggedUser.getPrincipal().getUsername(), content: message.content)
		template.convertAndSend("/queue/messages-"+user, privateMessage);
		
		new ResponseEntity(HttpStatus.OK)
	}
	
}
