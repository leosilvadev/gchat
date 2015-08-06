package br.leosilvadev.gchat.factories

import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import br.leosilvadev.gchat.model.domain.User

class EventTestFactory {

	static User buildUserSecurity(String name, String code){
		new User(name: name)
	}
	
	static UsernamePasswordAuthenticationToken buildAuthorization(String name, String code){
		new UsernamePasswordAuthenticationToken(buildUserSecurity(name, code), null)
	}
	
	static Message buildGenericMessage(Map<String, Object> headers){
		new GenericMessage<>("".getBytes(), headers)
	}
	
}
