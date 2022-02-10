package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Vehicle;

@Repository
public interface VehicleRepository extends CustomJpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
	
	List<Vehicle> findAll();

}
