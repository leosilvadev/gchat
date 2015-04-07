package br.leosilvadev.gchat.model.dto

import java.time.LocalDate
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

class Room {
	
	String code
	
	@NotNull @NotEmpty
	String name
	
	Calendar createdAt
	
	List users = []
	
	Room(){ this.createdAt=Calendar.getInstance() }
	
	def listeners
	
	def addUser(user){
		users << user
	}
	
	def generateCode(){
		code = UUID.randomUUID().toString().replace("-","")
	}
	
}
