package br.com.caelum.workshop.workshopapibolao.shared.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO que representa as informações de login enviadas pelo cliente
 * @author alberto
 *
 */
public class LoginDTO {

	@NotBlank
	@Email
	private String login;
	@NotBlank
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	@Override
	public String toString() {
		return "LoginDTO [login=" + login + "]";
	}
	
	

}
