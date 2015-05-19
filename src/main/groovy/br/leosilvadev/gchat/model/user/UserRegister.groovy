package br.leosilvadev.gchat.model.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.builder.UserBuilder
import br.leosilvadev.gchat.events.Publisher
import br.leosilvadev.gchat.events.UserRegisteredEvent
import br.leosilvadev.gchat.repositories.UserRepository

@Component
class UserRegister implements ApplicationListener<ChatUserRegisteringEvent> {
	
	@Autowired UserRepository repository
	@Autowired UserBuilder userBuilder
	@Autowired Publisher publisher
	
	void onApplicationEvent(ChatUserRegisteringEvent event) {
		def user = userBuilder.build(event.source)
								.onSuccess {println "User ${it.name} was saved succesfully!"}
								.onError {println "Error saving user ${it.name}"}
								.saveOn repository
								
//		publisher.publish(new UserRegisteredEvent(user))
	}

}
