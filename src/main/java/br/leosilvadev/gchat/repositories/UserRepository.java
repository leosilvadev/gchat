package br.leosilvadev.gchat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.leosilvadev.gchat.model.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findOneByEmail(String email);
	
	public User findOneByEmailAndPassword(String email, String password);
	
}
