package br.leosilvadev.gchat.model.dto

import groovy.transform.EqualsAndHashCode

import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

@EqualsAndHashCode(includes=["email"])
class ChatUser {

	Long id
	
	@NotNull @NotEmpty
	String name
	
	@NotNull @NotEmpty @Email
	String email
	
	@NotNull @NotEmpty
	String password
	
	@NotNull @NotEmpty
	String passwordConfirmation
	
	List rooms
	
}
