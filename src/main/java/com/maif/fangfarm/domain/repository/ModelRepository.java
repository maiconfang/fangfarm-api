package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Model;

@Repository
public interface ModelRepository extends CustomJpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {
	
	List<Model> findAll();
	List<Model> findAllByOrderByNameAsc();
	Page<Model> findAllByOrderByNameAsc(Pageable pageable);

}
