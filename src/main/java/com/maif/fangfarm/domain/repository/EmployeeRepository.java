package com.maif.fangfarm.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maif.fangfarm.domain.model.Employee;

@Repository
public interface EmployeeRepository extends CustomJpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	
	List<Employee> findAll();

}
