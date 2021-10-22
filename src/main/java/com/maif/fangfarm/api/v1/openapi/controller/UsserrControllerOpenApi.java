package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.UsserrModel;
import com.maif.fangfarm.api.v1.model.input.PasswordInput;
import com.maif.fangfarm.api.v1.model.input.UsserrWithPasswordInput;
import com.maif.fangfarm.api.v1.model.input.UsserrInput;
import com.maif.fangfarm.domain.filter.UsserrFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Users")
public interface UsserrControllerOpenApi {

	@ApiOperation("List of users")
	PagedModel<UsserrModel> list(UsserrFilter filter, Pageable pageable);
	
	@ApiOperation("Search a user by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of user invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	UsserrModel find(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long usserrId);

	@ApiOperation("Register an user")
	@ApiResponses({
		@ApiResponse(code = 201, message = "User registered"),
	})
	UsserrModel add(
			@ApiParam(name = "body", value = "Representation of a new user", required = true)
			UsserrWithPasswordInput usserrInput);
	
	@ApiOperation("Update an user by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "User updated"),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	UsserrModel update(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long usserrId,
			
			@ApiParam(name = "body", value = "Representation of a user with new data",
				required = true)
			UsserrInput usserrInput);

	@ApiOperation("Update a user's password")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Password changed successfully"),
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	void alterPassword(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long usserrId,
			
			@ApiParam(name = "body", value = "Representation of a new password", 
				required = true)
			PasswordInput password);

}