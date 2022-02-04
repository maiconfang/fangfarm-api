package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.BrandFilter;
import com.maif.fangfarm.domain.model.Brand;

public class BrandSpecs {

	public static Specification<Brand> withFilter(BrandFilter filter) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}

			if (filter.getModelId() != null) {
				predicates.add(builder.equal(root.get("model"), filter.getModelId()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	public static Specification<Brand> withIdModel(Long idModel) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (idModel != null) {
				predicates.add(builder.equal(root.get("model"), idModel));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
