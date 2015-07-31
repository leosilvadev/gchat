package br.leosilvadev.gchat.mail.dto

import groovy.transform.Immutable

@Immutable
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
