package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Brand;

@Repository
public interface BrandRepository extends CustomJpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {
	
	List<Brand> findAll();

}
