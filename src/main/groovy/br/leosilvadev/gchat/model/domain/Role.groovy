package br.leosilvadev.gchat.model.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty

@Entity
@Table(name="ROLES")
class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id
	
	@NotNull @NotEmpty
	String name
	
}
