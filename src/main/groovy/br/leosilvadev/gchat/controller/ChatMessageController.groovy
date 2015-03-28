package br.leosilvadev.gchat.controller

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

import br.leosilvadev.gchat.model.dto.Message
import br.leosilvadev.gchat.model.dto.PrivateMessage
import br.leosilvadev.gchat.model.dto.PublicMessage

@Controller
@RequestMapping("/chat")
class ChatMessageController {
	
	public static ConcurrentHashMap<String, String> users;
	
	@PostConstruct
	def init(){
		users = new ConcurrentHashMap<>()
	}
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@RequestMapping(value="/{room}/messages", method=RequestMethod.POST)
    def handlePublicMessage(
		@RequestBody Message message, 
		@PathVariable String room) {
		
		def publicMessage = new PublicMessage(room: room, from: message.from, content: message.content)
		template.convertAndSend("/topic/rooms-"+room, publicMessage)
		
		new ResponseEntity(HttpStatus.OK)
    }
		
	@RequestMapping(value="/messages/{user}", method=RequestMethod.POST)
	def handlePrivateMessage(
		@RequestBody Message message, 
		@PathVariable String user) throws Exception {
		
		def privateMessage = new PrivateMessage(to: user, from: message.from, content: message.content)
		template.convertAndSend("/queue/messages-"+user, privateMessage);
		
		new ResponseEntity(HttpStatus.OK)
	}
	
}
