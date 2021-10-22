package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.StateFilter;
import com.maif.fangfarm.domain.model.State;

public class StateSpecs {

	public static Specification<State> withFilter(StateFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}
			
			if (filter.getFs() != null) {
				predicates.add(builder.equal(root.get("fs"), filter.getFs()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
