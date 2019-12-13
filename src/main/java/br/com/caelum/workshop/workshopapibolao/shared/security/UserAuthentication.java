package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthentication implements Authentication {

    /**
	 * 
	 */
	private static final long serialVersionUID = -295250221436044510L;
	private final UserDetails user;
    private boolean authenticated = true;
	private Optional<String> password;

    public UserAuthentication(UserDetails user,Optional<String> password) {
        this.user = user;
		this.password = password;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return password.orElse(null);
    }

    @Override
    public Object getDetails() {
    	//pelo spring security poderia ser nulo
        return user;		
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}