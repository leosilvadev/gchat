package br.leosilvadev.gchat.model.domain

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
	
	def save(repository, successCallback, errorCallback){
		try {
			repository.save(this)
			if(successCallback) successCallback(this)
			
		} catch(ex){
			if(errorCallback) errorCallback(this, ex)
			else ex.printStackTrace()
			
		}
	}
	
	@Override
	int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	@Override
	boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
		
}
