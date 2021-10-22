package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.State;

@Repository
public interface StateRepository  extends CustomJpaRepository<State, Long>, JpaSpecificationExecutor<State> {
	
	List<State> findAll();

}
