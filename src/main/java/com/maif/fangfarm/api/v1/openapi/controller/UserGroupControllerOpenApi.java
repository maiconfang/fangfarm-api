package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.GroupppModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Users")
public interface UserGroupControllerOpenApi {

	@ApiOperation("Lists groups associated with a user")
	@ApiResponses({
		@ApiResponse(code = 404, message = "User not found", response = Problem.class)
	})
	CollectionModel<GroupppModel> list(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long usserrId);

	@ApiOperation("Disassociate of group with a user")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Disassociate performed successfully"),
		@ApiResponse(code = 404, message = "User or group not found", 
			response = Problem.class)
	})
	ResponseEntity<Void> disassociate(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long userId,
			
			@ApiParam(value = "ID of group", example = "1", required = true)
			Long groupId);

	@ApiOperation("Associate of group with user")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associate performed successfully"),
		@ApiResponse(code = 404, message = "User or group not found", 
			response = Problem.class)
	})
	ResponseEntity<Void> associate(
			@ApiParam(value = "ID of user", example = "1", required = true)
			Long userId,
			
			@ApiParam(value = "ID of group", example = "1", required = true)
			Long groupId);

}