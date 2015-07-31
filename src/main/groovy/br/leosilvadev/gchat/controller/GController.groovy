package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class GController {
	
	def badRequest(object){
		object ? new ResponseEntity(object, HttpStatus.BAD_REQUEST) : new ResponseEntity(HttpStatus.BAD_REQUEST)
	}
	
	def ok(object){
		object ? new ResponseEntity(object, HttpStatus.OK) : new ResponseEntity(HttpStatus.OK)
	}
	
	def created(object){
		object ? new ResponseEntity(object, HttpStatus.CREATED) : new ResponseEntity(HttpStatus.CREATED)
	}

}
