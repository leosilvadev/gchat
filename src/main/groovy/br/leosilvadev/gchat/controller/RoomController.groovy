package br.leosilvadev.gchat.controller

import javax.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import br.leosilvadev.gchat.events.NewRoomEvent
import br.leosilvadev.gchat.events.Publisher
import br.leosilvadev.gchat.model.dto.ChatRoom
import br.leosilvadev.gchat.model.services.RoomService


@RestController
@RequestMapping("/rooms")
class RoomController extends GController {
	
	@Autowired Publisher publisher
	@Autowired RoomService roomService
	
	@RequestMapping(method=RequestMethod.POST)
	def register(@Valid @RequestBody ChatRoom room, BindingResult bindingResult){
		if (bindingResult.hasErrors()) return badRequest()
		
		publisher.publish(new NewRoomEvent(room))
		created(room)
	}
	
	@RequestMapping(method=RequestMethod.GET)
	def list(@RequestParam(required=false) String roomName){
		def rooms = roomService.allWithName roomName
		ok(rooms)
	}

}
