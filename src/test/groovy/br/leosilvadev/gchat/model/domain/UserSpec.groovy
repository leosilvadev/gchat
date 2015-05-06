package br.leosilvadev.gchat.model.domain

import static org.junit.Assert.*
import static org.mockito.Mockito.verify
import spock.lang.Specification
import br.leosilvadev.gchat.repositories.UserRepository

class UserSpec extends Specification {

	UserRepository repository
	Closure closure

	def setup() {
		closure = Mock(Closure)
		repository = Mock(UserRepository)
	}
	
	def "Should save a user and execute callback"(){
		given: "A user"
			def user = new User()
		
		when: "The user is saved"
			user.save(repository, closure, null)

		then: "It have to be save in repository and execute the success callback"
			1 * repository.save(user)
			1 * closure.call(user)
	}
	
	def "Should NOT save a user and execute callback"(){
		def ex = new NullPointerException()
		given: "A user"
			def user = new User()
		
		when: "The user is saved"
			user.save(repository, null, closure)

		then: "It have to be save in repository and execute the error callback"
			1 * repository.save(user) >> { throw ex }
			1 * closure.call(user, ex)
	}
	
	def "Should check that two users are the same"(){
		def areSameUsers
		
		given: "Two users with same email"
			def userOne = new User(email: "user@user.com")
			def userTwo = new User(email: "user@user.com")
			
		when: "They are compared"
			areSameUsers = userOne.equals(userTwo)
			
		then: "They should be the same"
			areSameUsers
			userOne.equals(userTwo)
			userOne.hashCode() == userTwo.hashCode()
			
	}
	
	def "Should check that two users are differents"(){
		def areDifferentUsers
		
		given: "Two users with different email"
			def userOne = new User(email: "user@user.com")
			def userTwo = new User(email: "user2@user.com")
			
		when: "They are compared"
			areDifferentUsers = !userOne.equals(userTwo)
			
		then: "They should not be the same"
			areDifferentUsers
			userOne.hashCode() != userTwo.hashCode()
			!userOne.equals(null)
			!userOne.equals(new Room())
			
	}

}
