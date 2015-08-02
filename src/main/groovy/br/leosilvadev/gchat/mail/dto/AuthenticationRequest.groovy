package br.leosilvadev.gchat.mail.dto

import groovy.transform.EqualsAndHashCode;
import groovy.transform.Immutable

@Immutable
@EqualsAndHashCode
class AuthenticationRequest {

	String username
	String password
	
	def authenticated(){
		new AuthenticatedRequest(this, UUID.randomUUID().toString())
	}
	
	@Immutable
	static class AuthenticatedRequest {
		@Delegate AuthenticationRequest authentication
		String token
	}
	
}
