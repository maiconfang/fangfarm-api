package com.maif.fangfarm.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class MaifSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}
	
	public Long getUsserrId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usserr_id");
	}
	
	
	public boolean usserrAuthenticatedEqual(Long usserrId) {
		return getUsserrId() != null && usserrId != null
				&& getUsserrId().equals(usserrId);
	}
	
	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}
	
	public boolean hasWrittenScope() {
		return hasAuthority("SCOPE_WRITE");
	}
	
	public boolean hasReadingScope() {
		return hasAuthority("SCOPE_READ");
	}
	
	public boolean canConsultUsserrsGroupsPermissions() {
		return hasReadingScope() && hasAuthority("CONSULT_USSERRS_GROUPS_PERMISSIONS");
	}
	
	public boolean canEditUsserrsGroupsPermissions() {
		return hasWrittenScope() && hasAuthority("EDIT_USSERRS_GROUPS_PERMISSIONS");
	}
	
	public boolean canConsultCities() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultStates() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultModels() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultBrands() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultEmployees() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultVehicles() {
		return isAutenticado() && hasReadingScope();
	}
	
	public boolean canConsultAnimals() {
		return isAutenticado() && hasReadingScope();
	}
	
}
