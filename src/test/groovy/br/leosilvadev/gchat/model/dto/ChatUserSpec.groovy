package br.leosilvadev.gchat.model.dto

import static org.junit.Assert.*
import static org.mockito.Mockito.verify
import spock.lang.Specification
import br.leosilvadev.gchat.model.domain.Room
import br.leosilvadev.gchat.repositories.UserRepository

class ChatUserSpec extends Specification {

	UserRepository repository
	Closure closure

	def setup() {
		closure = Mock(Closure)
		repository = Mock(UserRepository)
	}
	
	def "Should check that two chat users are the same"(){
		def areSameUsers
		
		given: "Two chat users with same email"
			def userOne = new ChatUser(email: "user@user.com")
			def userTwo = new ChatUser(email: "user@user.com")
			
		when: "They are compared"
			areSameUsers = userOne.equals(userTwo)
			
		then: "They should be the same"
			areSameUsers
			userOne.hashCode() == userTwo.hashCode()
			
			
		when: "User one is compared with itself"
			areSameUsers = userOne.equals(userOne)
		
		then: "They should be the same"
			areSameUsers
			
	}
	
	def "Should check that two chat users are differents"(){
		def areDifferentUsers
		
		given: "Two chat users with different email"
			def userOne = new ChatUser(email: "user@user.com")
			def userTwo = new ChatUser(email: "user2@user.com")
			
		when: "They are compared"
			areDifferentUsers = !userOne.equals(userTwo)
			
		then: "They should not be the same"
			areDifferentUsers
			userOne.hashCode() != userTwo.hashCode()
			!userOne.equals(null)
			!userOne.equals(new Room())
			
	}

}
