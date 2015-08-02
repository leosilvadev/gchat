package br.leosilvadev.gchat.controller

import java.security.Principal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController;

import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.model.dto.Message
import br.leosilvadev.gchat.model.services.RoomService

@RestController
@RequestMapping("/rest/v1/chat")
class ChatMessageController extends GController {
	
	@Autowired SimpMessagingTemplate template
	@Autowired MessageBuilder builder
	@Autowired RoomService roomService
	
	@RequestMapping(value="/{room}/users", method=RequestMethod.GET)
	def listUsers(@PathVariable String room){
		ok(roomService.listUsersFrom(room))
	}
	
	@RequestMapping(value="/{room}/messages", method=RequestMethod.POST)
    def handlePublicMessage(
		@RequestBody Message message, 
		@PathVariable String room,
		Principal loggedUser) {
		
		template.convertAndSend("/topic/rooms-"+room, builder.publicMessage(message.content, room, loggedUser.getName()))
		
		created()
    }

	@RequestMapping(value="/messages/{user}", method=RequestMethod.POST)
	def handlePrivateMessage(
		@RequestBody Message message, 
		@PathVariable String user,
		Principal loggedUser) throws Exception {
		
		template.convertAndSend("/queue/messages-"+user, builder.privateMessage(message, user, loggedUser.getName()))
		
		created()
	}
	
}
