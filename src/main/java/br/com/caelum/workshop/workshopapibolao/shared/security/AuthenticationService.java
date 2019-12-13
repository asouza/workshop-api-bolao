package br.com.caelum.workshop.workshopapibolao.shared.security;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "select user from SystemUser user join fetch user.roles where user.login = :login ";

		try {
			SystemUser foundUser = manager.createQuery(jpql, SystemUser.class)
			.setParameter("login", username)
			.getSingleResult();
			
			return new SystemUserDetails(foundUser);
			
		} catch (NoResultException e) {
			throw new UsernameNotFoundException("O usuário " + username + " não foi encontrado!");
		}
	}

}
