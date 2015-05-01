package br.leosilvadev.gchat.model.dto

import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

class ChatRoom {
	
	String code
	
	@NotNull @NotEmpty
	String name
	
	Calendar createdAt
	
	List users = []
	
}
