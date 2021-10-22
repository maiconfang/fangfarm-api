package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.GroupFilter;
import com.maif.fangfarm.domain.model.Grouppp;

public class GroupSpecs {

	public static Specification<Grouppp> withName(GroupFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
