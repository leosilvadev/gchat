package br.leosilvadev.gchat.events

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component

@Component
class Publisher {

	@Autowired ApplicationContext applicationContext
	
	def publish(ApplicationEvent event){
		applicationContext.publishEvent(event)
	}
	
}
