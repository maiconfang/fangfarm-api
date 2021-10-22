package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.UsserrFilter;
import com.maif.fangfarm.domain.model.Usserr;

public class UsserrSpecs {

	public static Specification<Usserr> withNameOrEmailOrAnd(UsserrFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}
			
			if (filter.getEmail() != null) {
				predicates.add(builder.like(root.get("email"), "%" + filter.getEmail() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
