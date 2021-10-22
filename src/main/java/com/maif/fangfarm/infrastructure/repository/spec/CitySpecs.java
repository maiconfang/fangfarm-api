package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.CityFilter;
import com.maif.fangfarm.domain.model.City;

public class CitySpecs {

	public static Specification<City> withFilter(CityFilter filter) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}

			if (filter.getStateId() != null) {
				predicates.add(builder.equal(root.get("state"), filter.getStateId()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	public static Specification<City> withIdState(Long idState) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (idState != null) {
				predicates.add(builder.equal(root.get("state"), idState));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
