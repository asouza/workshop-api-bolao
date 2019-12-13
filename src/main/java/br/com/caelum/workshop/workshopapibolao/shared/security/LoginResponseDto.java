package br.com.caelum.workshop.workshopapibolao.shared.security;

public class LoginResponseDto {

	private String token;

	public LoginResponseDto(SystemUserDetails details) {
		this.token = new TokenHandler().createTokenForUser(details);
	}
	
	public String getToken() {
		return token;
	}
	

	
}
