package com.maif.fangfarm.core.security.authorizationserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.model.Usserr;
import com.maif.fangfarm.domain.repository.UsserrRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsserrRepository usserrRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usserr usserr = usserrRepository.findByEmail(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with e-mail entered"));
		
		return new AuthUser(usserr, getAuthorities(usserr));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Usserr usserr) {
		return usserr.getGroups().stream()
				.flatMap(group -> group.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName().toUpperCase()))
				.collect(Collectors.toSet());
	}

}
