package br.leosilvadev.gchat.mail.dto

import groovy.transform.Immutable

import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

@Immutable()
class Authentication {
	
	@NotNull @NotEmpty
	String username
	
	@NotNull @NotEmpty
	String password
	
	def authenticated(){
		new Authenticated(this, UUID.randomUUID().toString())
	}
	
	
	@Immutable
	static class Authenticated {
		
		@Delegate Authentication authentication
		
		String token
		
	}

}
