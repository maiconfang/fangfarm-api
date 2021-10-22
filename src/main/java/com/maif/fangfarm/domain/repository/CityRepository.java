package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.City;

@Repository
public interface CityRepository extends CustomJpaRepository<City, Long>, JpaSpecificationExecutor<City> {
	
	List<City> findAll();

}
