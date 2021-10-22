package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.PermissionFilter;
import com.maif.fangfarm.domain.model.Permission;

public class PermissionSpecs {

	public static Specification<Permission> withDescription(PermissionFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getDescription() != null) {
				predicates.add(builder.like(root.get("description"), "%" + filter.getDescription() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
