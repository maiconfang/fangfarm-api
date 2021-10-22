package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.EmployeeModel;
import com.maif.fangfarm.api.v1.model.input.EmployeeInput;
import com.maif.fangfarm.domain.filter.EmployeeFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Employee")
public interface EmployeeControllerOpenApi {

	@ApiOperation("Lis of Employees")
	PagedModel<EmployeeModel> list(EmployeeFilter filter, Pageable pageable);
	
	@ApiOperation("Find employee by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of employee invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Employee not found", response = Problem.class)
	})
	EmployeeModel find(
			@ApiParam(value = "ID of employee", example = "1", required = true)
			Long employeeId);
	
	@ApiOperation("Register a employee")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Employee registered"),
	})
	EmployeeModel add(
			@ApiParam(name = "body", value = "Representation of a new employee", required = true)
			EmployeeInput employeeInput);
	
	@ApiOperation("Update the employee by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Employee updated"),
		@ApiResponse(code = 404, message = "Employee not found", response = Problem.class)
	})
	EmployeeModel update(
			@ApiParam(value = "ID of employee", example = "1", required = true) 
			Long employeeId,
			
			@ApiParam(name = "body", value = "Representation of a new employee with new data", required = true)
			EmployeeInput employeeInput);
	
	@ApiOperation("Remove employee by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Employee removed"),
		@ApiResponse(code = 404, message = "Employee not found", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID of employee", example = "1", required = true)
			Long employeeId);
	
}
