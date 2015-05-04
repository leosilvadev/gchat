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

}
