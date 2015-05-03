package br.leosilvadev.gchat.wrapper

import org.springframework.web.socket.messaging.SessionSubscribeEvent

import spock.lang.Specification
import br.leosilvadev.gchat.factories.EventTestFactory

class EventWrapperSpec extends Specification {
	
	def "Should get the correct Event Sender"(){
		given: "A User sending an Event"
			def authorization = EventTestFactory.buildAuthorization("FAKE", "1234")
			def headers = [simpUser: authorization]
		
		when: "The event is wrapped"
			def message = EventTestFactory.buildGenericMessage(headers)
			def event = new SessionSubscribeEvent("", message)
			def wrapper = new EventWrapper(event)
		
		then: "It should bring the correct sender"
			wrapper.sender().equals(authorization.getPrincipal())
	}
	
	
	
	def "Should get the correct Event Destination"(){
		given: "A User sending an Event"
			def destination = "/topic/rooms-1234"
			def headers = [simpDestination: destination]
		
		when: "The event is wrapped"
			def message = EventTestFactory.buildGenericMessage(headers)
			def event = new SessionSubscribeEvent("", message)
			def wrapper = new EventWrapper(event)
		
		then: "It should bring the correct sender"
			wrapper.destination().equals(destination)
	}
	
	
	
	def "Should confirm that Event Destination is a Room Subscriber"(){
		given: "A User sending an Event"
			def headers = [simpDestination: "/topic/rooms-1234"]
		
		when: "The event is wrapped"
			def message = EventTestFactory.buildGenericMessage(headers)
			def event = new SessionSubscribeEvent("", message)
			def wrapper = new EventWrapper(event)
		
		then: "It should bring the correct sender"
			wrapper.isRoomsSubscriber()
	}
	
	
	
	def "Should confirm that Event Destination is NOT a Room Subscriber"(){
		given: "A User sending an Event"
			def headers = [simpDestination: "/topic/anyway-1234"]
		
		when: "The event is wrapped"
			def message = EventTestFactory.buildGenericMessage(headers)
			def event = new SessionSubscribeEvent("", message)
			def wrapper = new EventWrapper(event)
		
		then: "It should bring the correct sender"
			!wrapper.isRoomsSubscriber()
	}
	
	
	
	def "Should get the correct Event Room Code"(){
		given: "A User sending an Event"
			def nativeHeaders = [roomCode: ["12344321"]]
			def headers = [nativeHeaders: nativeHeaders]
		
		when: "The event is wrapped"
			def message = EventTestFactory.buildGenericMessage(headers)
			def event = new SessionSubscribeEvent("", message)
			def wrapper = new EventWrapper(event)
		
		then: "It should bring the correct sender"
			wrapper.roomCode().equals("12344321")
	}

}
