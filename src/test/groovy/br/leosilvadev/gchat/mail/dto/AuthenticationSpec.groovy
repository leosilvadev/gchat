package br.leosilvadev.gchat.mail.dto

import spock.lang.Specification

class AuthenticationSpec extends Specification {

	def "Should validate two objects with same username and password"(){
		given: "Two Authentication objects"
			def auth1 = new Authentication("admin", "secret")
			def auth2 = new Authentication("admin", "secret")
			
		when: "The objects are compared"
			def areEquals = auth1.equals(auth2)
			
		then: "The must be equals"
			areEquals
	}
	
	def "Should validate two objects with same username but different password"(){
		given: "Two Authentication objects"
			def auth1 = new Authentication("admin", "secret")
			def auth2 = new Authentication("admin", "secret2")
			
		when: "The objects are compared"
			def areEquals = auth1.equals(auth2)
			
		then: "The must be equals"
			areEquals == false
	}
	
	def "Should validate two objects with same password but different username"(){
		given: "Two Authentication objects"
			def auth1 = new Authentication("admin", "secret")
			def auth2 = new Authentication("admin2", "secret")
			
		when: "The objects are compared"
			def areEquals = auth1.equals(auth2)
			
		then: "The must be equals"
			areEquals == false
	}
	
	def "Should validate two objects with different types"(){
		given: "Two objects"
			def auth = new Authentication("admin", "secret")
			def object = new Object()
			
		when: "The objects are compared"
			def areEquals = auth.equals(object)
			
		then: "The must be equals"
			areEquals == false
	}
	
	def "Should validate two objects with null object"(){
		given: "One object"
			def auth = new Authentication("admin", "secret")
			
		when: "The objects are compared"
			def areEquals = auth.equals(null)
			
		then: "The must be equals"
			areEquals == false
	}
}
