package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.GroupppModel;
import com.maif.fangfarm.api.v1.model.input.GroupppInput;
import com.maif.fangfarm.domain.filter.GroupFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups")
public interface GroupControllerOpenApi {

	@ApiOperation("List of group")
	PagedModel<GroupppModel> list(GroupFilter filter, Pageable pageable);
	
	@ApiOperation("Find a group by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of group invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	GroupppModel find(
			@ApiParam(value = "ID of a group", example = "1", required = true)
			Long groupId);
	
	@ApiOperation("Register a group")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Group registered"),
	})
	GroupppModel add(
			@ApiParam(name = "body", value = "Representation of a new group", required = true)
			GroupppInput groupInput);
	
	@ApiOperation("Update a group by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Group updated"),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	GroupppModel update(
			@ApiParam(value = "ID of the group", example = "1", required = true)
			Long groupId,
			
			@ApiParam(name = "body", value = "Representation of a new group with new data", 
				required = true)
			GroupppInput groupInput);
	
	@ApiOperation("Remove a group by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Group removed"),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of the group", example = "1", required = true)
			Long groupId);
	
}
