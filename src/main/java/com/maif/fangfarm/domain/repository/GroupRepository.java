package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Grouppp;

@Repository
public interface GroupRepository extends CustomJpaRepository<Grouppp, Long>, JpaSpecificationExecutor<Grouppp> {
	
	
	List<Grouppp> findAll();

}
