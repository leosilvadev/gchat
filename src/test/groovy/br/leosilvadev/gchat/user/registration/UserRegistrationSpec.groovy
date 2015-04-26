package br.leosilvadev.gchat.user.registration

import static org.junit.Assert.*
import geb.spock.GebSpec

import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration

import br.leosilvadev.gchat.Application

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
class UserRegistrationSpec extends GebSpec {
	
	def "Should not accept different passwords"(){
		given: "A user in registration page"
			go "/#register"
			waitFor { $('#modal-registration').displayed }
	
		when: "User try to register himself"
			$('input#name').value("NewUser")
			$('input#email').value("newuser@gmail.com")
			$('input#password').value("newuser")
			$('input#passwordConfirmation').value("newuser2")
			$('button#btn-register').click()
			
		then: "A message about different passwords must be visible"
			$('div#registration-messages div.alert > span.message').text() == "Passwords does not match"
		
	}

}
