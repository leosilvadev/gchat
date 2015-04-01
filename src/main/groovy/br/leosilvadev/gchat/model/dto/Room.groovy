package br.leosilvadev.gchat.model.dto

import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

class Room {
	
	String code
	
	@NotNull @NotEmpty
	String name
	
	List users = []
	
	def listeners
	
	def addUser(user){
		users << user
	}
	
	def generateCode(){
		this.code = UUID.randomUUID().toString()
	}
	
}
