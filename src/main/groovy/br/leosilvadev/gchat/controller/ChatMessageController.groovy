package br.leosilvadev.gchat.controller

import java.util.concurrent.ConcurrentHashMap

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

import br.leosilvadev.gchat.model.dto.Message;
import br.leosilvadev.gchat.model.dto.PrivateMessage;
import br.leosilvadev.gchat.model.dto.PublicMessage;

@Controller
class ChatMessageController {
	
	public static ConcurrentHashMap<String, String> users;
	
	@PostConstruct
	def init(){
		users = new ConcurrentHashMap<>()
	}
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/public/messages")
    Message handlePublicMessage(PublicMessage message) throws Exception {
		template.convertAndSend("/topic/rooms-"+message.room, message);
    }
	
	@MessageMapping("/private/messages")
	Message handlePrivateMessage(PrivateMessage message) throws Exception {
		template.convertAndSend("/queue/messages-"+message.to, message);
	}
	
}
