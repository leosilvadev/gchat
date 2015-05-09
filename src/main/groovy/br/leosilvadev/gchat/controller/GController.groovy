package br.leosilvadev.gchat.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class GController {
	
	def badRequest(){
		new ResponseEntity(HttpStatus.BAD_REQUEST)
	}
	
	def ok(object){
		new ResponseEntity(object, HttpStatus.OK)
	}
	
	def created(object){
		new ResponseEntity(object, HttpStatus.CREATED)
	}

}
