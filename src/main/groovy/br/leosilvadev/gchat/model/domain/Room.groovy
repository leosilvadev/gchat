package br.leosilvadev.gchat.model.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

@Entity
@Table(name="ROOMS")
class Room {
	
	@Id
	String code
	
	@NotNull @NotEmpty
	String name
	
	@NotNull
	Calendar createdAt
	
	@ManyToMany
	@JoinTable(name="ROOM_USERS")
	List<User> users = []
	
	@ManyToOne
	@JoinColumn(name="OWNER")
	User owner
	
	def addUser(user){
		users << user
	}
	
	@PrePersist
	void prepareNewRoom(){
		code = UUID.randomUUID().toString().replace("-", "")
		createdAt = Calendar.getInstance()
	}
	
}