package br.com.caelum.workshop.workshopapibolao.shared.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorSecurityThings {
	
	@PersistenceContext
	private EntityManager manager;

	
	@GetMapping(value = "/magic/roles")
	@Transactional
	public String generateDefaultRoles() {
		manager.persist(Role.PARTICIPANTE);
		return "role gerada";
	}
	
	@GetMapping(value = "/magic/new-user")
	@Transactional
	public String getMethodName(@RequestParam String login,@RequestParam String password) {
		String passwordEncoded = new BCryptPasswordEncoder().encode(password);
		Role roleParticipante = manager.createQuery("select r from Role r where r.name = 'ROLE_PARTICIPANTE'", Role.class).getSingleResult();
		manager.persist(new SystemUser(login,passwordEncoded,roleParticipante));
		return String.format("usuario %s criado", login);
	}


}
