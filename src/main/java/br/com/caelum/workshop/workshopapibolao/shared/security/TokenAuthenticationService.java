package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

public class TokenAuthenticationService {

	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private TokenHandler tokenHandler = new TokenHandler();
	private UserDetailsService userDetailsService;

	public TokenAuthenticationService(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final UserDetails user = (UserDetails) authentication.getPrincipal();
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Optional<UserAuthentication> getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTH_HEADER_NAME);
		if (!StringUtils.hasText(token)) {
			token = request.getParameter(AUTH_HEADER_NAME);
		}
		if (token != null) {
			UserDetails user = tokenHandler.parseUserFromToken(token, userDetailsService::loadUserByUsername);
			if (user != null) {
				return Optional.of(new UserAuthentication(user, Optional.empty()));
			}
		}
		return Optional.empty();
	}
}