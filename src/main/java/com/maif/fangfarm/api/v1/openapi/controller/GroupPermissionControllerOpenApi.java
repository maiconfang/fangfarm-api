package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.PermissionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Groups")
public interface GroupPermissionControllerOpenApi {
	
	@ApiOperation("List the permissions associated with a group")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of group invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Group not found", response = Problem.class)
	})
	CollectionModel<PermissionModel> list(
			@ApiParam(value = "ID of group", example = "1", required = true)
			Long groupId);

	@ApiOperation("Disassociate of permission with group")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Disassociate performed successfully"),
		@ApiResponse(code = 404, message = "Group or permission not found", 
			response = Problem.class)
	})
	ResponseEntity<Void> disassociate(
			@ApiParam(value = "ID of group", example = "1", required = true)
			Long groupId,
			
			@ApiParam(value = "ID of permission", example = "1", required = true)
			Long permissionId);

	@ApiOperation("Associate of permission with group ")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associate performed successfully"),
		@ApiResponse(code = 404, message = "Group or permission not found ", 
			response = Problem.class)
	})
	ResponseEntity<Void> associate(
			@ApiParam(value = "ID of group", example = "1", required = true)
			Long groupId,
			
			@ApiParam(value = "ID of permission", example = "1", required = true)
			Long permissionId);

}