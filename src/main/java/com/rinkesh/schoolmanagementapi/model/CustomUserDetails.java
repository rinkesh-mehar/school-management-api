package com.rinkesh.schoolmanagementapi.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import com.rinkesh.schoolmanagementapi.model.Users;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	private Users users;

	public CustomUserDetails(final User _user, Users users) {
		this.user = _user;
		this.users = users;
	}

	public CustomUserDetails() {
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();

		if (user != null) {
			_grntdAuths = user.getAuthorities();
		}

		return _grntdAuths;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		if (this.user == null) {
			return null;
		}
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

	public Users getUser() {
		return users;
	}


	@Override
	public String toString() {
		return "CustomUserDetails [user=" + user + "]";
	}

}