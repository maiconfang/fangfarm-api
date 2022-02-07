package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.StateModel;
import com.maif.fangfarm.api.v1.model.input.StateInput;
import com.maif.fangfarm.domain.filter.StateFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "States")
public interface StateControllerOpenApi {

	@ApiOperation("List the states")
	PagedModel<StateModel> list(StateFilter filter, Pageable pageable);
	
	@ApiOperation("List the states without pagination")
	CollectionModel<StateModel> listNoPagination(StateFilter filter);

	@ApiOperation("Find the state by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of state invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "State not found", response = Problem.class)
	})
	StateModel find(
			@ApiParam(value = "ID of state", example = "1", required = true)
			Long stateId);

	@ApiOperation("Register a state")
	@ApiResponses({
		@ApiResponse(code = 201, message = "State registered"),
	})
	StateModel add(
			@ApiParam(name = "body", value = "Representation of the new state", required = true)
			StateInput stateInput);

	@ApiOperation("Update the state by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "State updated"),
		@ApiResponse(code = 404, message = "State not found", response = Problem.class)
	})
	StateModel update(
			@ApiParam(value = "ID of the state", example = "1", required = true)
			Long stateId,
			
			@ApiParam(name = "body", value = "Representation of the state with new data", required = true)
			StateInput stateInput);

	@ApiOperation("Remove the state by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "State removed"),
		@ApiResponse(code = 404, message = "State not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of the state", example = "1", required = true)
			Long stateId);

}