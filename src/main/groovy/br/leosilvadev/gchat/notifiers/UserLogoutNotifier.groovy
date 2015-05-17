package br.leosilvadev.gchat.notifiers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

import br.leosilvadev.gchat.builder.MessageBuilder
import br.leosilvadev.gchat.constants.ChatConstants;
import br.leosilvadev.gchat.model.dto.ChatUser

/**
 * Created by leonardo on 08/05/15.
 */

@Component
class UserLogoutNotifier {

    @Autowired MessageBuilder builder
    @Autowired SimpMessagingTemplate template

    LogoutNotifierRequest to(roomCode){
        new LogoutNotifierRequest(roomCode: roomCode, template: template, builder: builder)
    }

    static class LogoutNotifierRequest {
        def roomCode
        def messageContent
        def chatUser

        def template
        def builder

        LogoutNotifierRequest by(user){
            messageContent = "<b>${user.name}</b> left the room... :(".toString()
            chatUser = new ChatUser(id: user.id, name: user.name, email: user.email)
            this
        }

        void send(){
            def topic = "/topic/rooms-${roomCode}".toString()
            template.convertAndSend(topic, builder.newUser(messageContent, chatUser, ChatConstants.SYSTEM_MESSAGE))
        }
    }

}
