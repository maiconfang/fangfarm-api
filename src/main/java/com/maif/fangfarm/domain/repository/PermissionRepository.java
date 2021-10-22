package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Permission;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
	
	List<Permission> findAll();

}
