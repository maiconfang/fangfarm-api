package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.CityModel;
import com.maif.fangfarm.api.v1.model.input.CityInput;
import com.maif.fangfarm.domain.filter.CityFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

	@ApiOperation("List the cities")
	PagedModel<CityModel> list(CityFilter filter, Pageable pageable);
	
	@ApiOperation("List the cities")
	CollectionModel<CityModel> list(Long cityId);
	
	
	@ApiOperation("Find a city by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of city invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "City not found", response = Problem.class)
	})
	CityModel find(
			@ApiParam(value = "ID of a city", example = "1", required = true)
			Long cityId);
	
	@ApiOperation("Register a city")
	@ApiResponses({
		@ApiResponse(code = 201, message = "City registered"),
	})
	CityModel add(
			@ApiParam(name = "body", value = "Representation of a new city", required = true)
			CityInput cityInput);
	
	@ApiOperation("Update a city by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "City updated"),
		@ApiResponse(code = 404, message = "City not found ", response = Problem.class)
	})
	CityModel update(
			@ApiParam(value = "ID of a city", example = "1", required = true) 
			Long cityId,
			
			@ApiParam(name = "body", value = "Representation of a new city with new data", required = true)
			CityInput cityInput);
	
	@ApiOperation("Remove the city by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "City removed"),
		@ApiResponse(code = 404, message = "City not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of a city", example = "1", required = true)
			Long cityId);
	
}
