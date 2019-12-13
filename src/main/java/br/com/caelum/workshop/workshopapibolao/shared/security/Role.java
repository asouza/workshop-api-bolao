package br.com.caelum.workshop.workshopapibolao.shared.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322926734171829137L;
	public static final Role PARTICIPANTE = new Role("ROLE_PARTICIPANTE");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String name;

	/**
	 * @deprecated para os frameworks
	 */
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
