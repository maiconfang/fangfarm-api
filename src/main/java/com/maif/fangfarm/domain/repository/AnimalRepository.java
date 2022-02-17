package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Animal;

@Repository
public interface AnimalRepository  extends CustomJpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {
	
	List<Animal> findAll();

}
