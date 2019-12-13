package br.com.caelum.workshop.workshopapibolao.shared.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SystemUserDetails implements UserDetails {

	private SystemUser systemUser;

	public SystemUserDetails(SystemUser systemUser) {
		super();
		this.systemUser = systemUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return systemUser.getRoles();
	}

	@Override
	public String getPassword() {
		return systemUser.getPassword();
	}

	@Override
	public String getUsername() {
		return systemUser.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	
	

}
