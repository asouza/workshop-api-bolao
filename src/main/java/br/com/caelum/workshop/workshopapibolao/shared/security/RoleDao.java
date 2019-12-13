package br.com.caelum.workshop.workshopapibolao.shared.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer>{

	public Role findByName(String name);
}
