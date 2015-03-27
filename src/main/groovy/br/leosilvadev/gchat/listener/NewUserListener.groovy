package br.leosilvadev.gchat.listener

import java.util.LinkedList;
import java.util.Map;

import org.springframework.context.ApplicationListener
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent

@Component
class NewUserListener implements ApplicationListener<SessionConnectedEvent> {

	void onApplicationEvent(SessionConnectedEvent event) {
		GenericMessage simpConnectMessage = (GenericMessage) event.getMessage().getHeaders().get("simpConnectMessage");
		Map nativeHeaders = (Map) simpConnectMessage.getHeaders().get("nativeHeaders");
		
		String sessionId = String.valueOf( event.getMessage().getHeaders().get("simpSessionId") );
		LinkedList<String> usernameParam = (LinkedList<String>) nativeHeaders.get("username");
		if(usernameParam!=null && !usernameParam.isEmpty()) {
			
		}
	}

}
