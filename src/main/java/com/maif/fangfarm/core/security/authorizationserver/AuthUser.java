package com.maif.fangfarm.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.maif.fangfarm.domain.model.Usserr;

import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullName;
	
	public AuthUser(Usserr usserr, Collection<? extends GrantedAuthority> authorities) {
		super(usserr.getEmail(), usserr.getPassword(), authorities);
		
		this.userId = usserr.getId();
		this.fullName = usserr.getName();
	}
	
}
