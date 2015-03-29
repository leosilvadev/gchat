package br.leosilvadev.gchat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.leosilvadev.gchat.model.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role findOneByName(String name);
	
}
