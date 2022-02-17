package com.maif.fangfarm.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	
	public @interface Cities {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CITIES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultCities()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface Brands {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_BRANDS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultBrands()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface States {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STATES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultStates()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface Models {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_MODELS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultModels()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface Employees {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_EMPLOYEES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultEmployees()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface UsersGroupPermission {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @maifSecurity.usserrAuthenticatedEqual(#usserrId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEditPropertyPassword { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USSERRS_GROUPS_PERMISSIONS') or "
				+ "@maifSecurity.usserrAuthenticatedEqual(#usserrId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEditUser { }

		@PreAuthorize("@maifSecurity.canEditUsserrsGroupsPermissions()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }
		

		@PreAuthorize("@maifSecurity.canConsultUsserrsGroupsPermissions()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface Vehicles {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_VEHICLES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultVehicles()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	public @interface Animals {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_ANIMALS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanEdit { }

		@PreAuthorize("@maifSecurity.canConsultAnimals()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface CanConsult { }
		
	}
	
	
}
