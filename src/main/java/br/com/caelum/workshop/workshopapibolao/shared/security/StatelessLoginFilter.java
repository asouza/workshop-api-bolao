package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;

	protected StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final LoginDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				login.getLogin(), login.getPassword());		
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, (UserAuthentication)authentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}