package br.leosilvadev.gchat.model.domain;

import static org.junit.Assert.*

import org.junit.Test

import spock.lang.Specification

class RoomSpec extends Specification {

	def "Should create a room properly"(){
		given: "A user"
			def user = new User(email: "fake@fake.com")
			def room = new Room().withName("Fake").createdBy(user)
		
		when: "It is saved"
			room.prepareNewRoom()
		
		then: "All properties must be setted OK"
			!room.name.empty
			!room.code.empty
			room.createdAt != null
			room.owner != null
			room.name.equals "Fake"
			room.owner.equals user
	}
	
}
