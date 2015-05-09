package br.leosilvadev.gchat.model.domain

import groovy.transform.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany;
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USERS")
@EqualsAndHashCode(includes=["email"])
class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id
	
	@NotNull @NotEmpty
	String name
	
	@Column(unique=true)
	@NotNull @NotEmpty @Email
	String email
	
	@NotNull @NotEmpty
	String password
	
	@ManyToMany
	Set<Role> roles
	
	Boolean locked
	
	def save(repository, successCallback, errorCallback){
		try {
			repository.save(this)
			if(successCallback) successCallback(this)
			
		} catch(ex){
			if(errorCallback) errorCallback(this, ex)
			else ex.printStackTrace()
			
		}
	}
		
}
