package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.VehicleModel;
import com.maif.fangfarm.api.v1.model.input.VehicleInput;
import com.maif.fangfarm.domain.filter.VehicleFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Vehicles")
public interface VehicleControllerOpenApi {

	@ApiOperation("List the vehicles")
	PagedModel<VehicleModel> list(VehicleFilter filter, Pageable pageable);
	
	@ApiOperation("List the vehicles")
	CollectionModel<VehicleModel> list(Long vehicleId);
	
	
	@ApiOperation("Find a vehicle by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of vehicle invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Vehicle not found", response = Problem.class)
	})
	VehicleModel find(
			@ApiParam(value = "ID of a vehicle", example = "1", required = true)
			Long vehicleId);
	
	@ApiOperation("Register a vehicle")
	@ApiResponses({
		@ApiResponse(code = 201, message = "vehicle registered"),
	})
	VehicleModel add(
			@ApiParam(name = "body", value = "Representation of a new vehicle", required = true)
			VehicleInput vehicleInput);
	
	@ApiOperation("Update a vehicle by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Vehicle updated"),
		@ApiResponse(code = 404, message = "Vehicle not found ", response = Problem.class)
	})
	VehicleModel update(
			@ApiParam(value = "ID of a vehicle", example = "1", required = true) 
			Long vehicleId,
			
			@ApiParam(name = "body", value = "Representation of a new vehicle with new data", required = true)
			VehicleInput vehicleInput);
	
	@ApiOperation("Remove the vehicle by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Vehicle removed"),
		@ApiResponse(code = 404, message = "Vehicle not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of a Vehicle", example = "1", required = true)
			Long vehicleId);
	
}
