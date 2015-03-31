package br.leosilvadev.gchat.model.dto

class Room {
	
	String code
	String name
	List users = []
	
	def listeners
	
	def addUser(user){
		users << user
	}
	
}
