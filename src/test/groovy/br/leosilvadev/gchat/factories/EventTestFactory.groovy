package br.leosilvadev.gchat.factories

import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import br.leosilvadev.gchat.model.domain.User
import br.leosilvadev.gchat.security.UserSecurity

class EventTestFactory {

	static UserSecurity buildUserSecurity(String name, String code){
		new UserSecurity(new User(name: name), code)
	}
	
	static UsernamePasswordAuthenticationToken buildAuthorization(String name, String code){
		new UsernamePasswordAuthenticationToken(buildUserSecurity(name, code), null)
	}
	
	static Message buildGenericMessage(Map<String, Object> headers){
		new GenericMessage<>("".getBytes(), headers)
	}
	
}
