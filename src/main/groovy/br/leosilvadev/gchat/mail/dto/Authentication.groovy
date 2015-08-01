package br.leosilvadev.gchat.mail.dto

import groovy.transform.EqualsAndHashCode;
import groovy.transform.Immutable

@Immutable
@EqualsAndHashCode
class Authentication {

	String username
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
