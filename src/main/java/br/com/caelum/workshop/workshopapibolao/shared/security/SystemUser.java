package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@Email
	private @NotBlank String login;

	@NotBlank
	@Length(min = 6)
	private String password;
	@ManyToMany
	private Set<Role> roles = new HashSet<>();

	@Deprecated
	public SystemUser() {

	}
	
	//FIXME como o ponto que chama esse construtor sabe que tem passar um password encodado? pq não é o texto original?
	public SystemUser(@NotBlank @Email String login, @NotBlank @Length(min = 6) String password,
			@Size(min = 1) Role... roles) {
		this.login = login;
		this.password = password;
		Stream.of(roles).forEach(this.roles::add);
	}

	public String getLogin() {
		return login;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

}
