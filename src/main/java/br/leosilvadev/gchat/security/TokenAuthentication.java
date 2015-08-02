package br.leosilvadev.gchat.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.leosilvadev.gchat.model.domain.User;

public class TokenAuthentication implements Authentication {

	private static final long serialVersionUID = -5244389466947145831L;
	
	private User user;
	private String token;
	private boolean authenticated;
	
	public TokenAuthentication(User user, String token, boolean authenticated) {
		this.user = user;
		this.token = token;
		this.authenticated = authenticated;
	}
	
	@Override
	public String getName() {
		return user.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user
				.getRoles()
				.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return token!=null && !token.isEmpty() && authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}

}
