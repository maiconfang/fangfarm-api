package com.maif.fangfarm.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.maif.fangfarm.domain.filter.VehicleFilter;
import com.maif.fangfarm.domain.model.Vehicle;

public class VehicleSpecs {

	public static Specification<Vehicle> withFilter(VehicleFilter filter) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (filter.getLicensePlate() != null) {
				predicates.add(builder.like(root.get("licensePlate"), "%" + filter.getLicensePlate() + "%"));
			}

			if (filter.getBrandId() != null) {
				predicates.add(builder.equal(root.get("brand"), filter.getBrandId()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	public static Specification<Vehicle> withIdBrand(Long idBrand) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (idBrand != null) {
				predicates.add(builder.equal(root.get("brand"), idBrand));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
