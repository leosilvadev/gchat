package br.leosilvadev.gchat.model.dto

import br.leosilvadev.gchat.utils.Time;

class Message {
	
	String from
	String content
	String roomCode
	String sentAt = Time.now()
		
}
