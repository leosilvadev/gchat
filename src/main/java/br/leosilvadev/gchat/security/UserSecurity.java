package br.leosilvadev.gchat.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.leosilvadev.gchat.model.domain.User;

public class UserSecurity implements UserDetails {

	private static final long serialVersionUID = 2441856620861827373L;

	private User user;
	
	private String code;
	
	public UserSecurity(User user, String code) {
		this.user = user;
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
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

}
