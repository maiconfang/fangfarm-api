package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.BrandModel;
import com.maif.fangfarm.api.v1.model.input.BrandInput;
import com.maif.fangfarm.domain.filter.BrandFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Brands")
public interface BrandControllerOpenApi {

	@ApiOperation("List the brands")
	PagedModel<BrandModel> list(BrandFilter filter, Pageable pageable);
	
	@ApiOperation("List the brands")
	CollectionModel<BrandModel> list(Long brandId);
	
	
	@ApiOperation("Find a brand by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of brand invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Brand not found", response = Problem.class)
	})
	BrandModel find(
			@ApiParam(value = "ID of a brand", example = "1", required = true)
			Long brandId);
	
	@ApiOperation("Register a brand")
	@ApiResponses({
		@ApiResponse(code = 201, message = "brand registered"),
	})
	BrandModel add(
			@ApiParam(name = "body", value = "Representation of a new brand", required = true)
			BrandInput brandInput);
	
	@ApiOperation("Update a brand by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Brand updated"),
		@ApiResponse(code = 404, message = "Brand not found ", response = Problem.class)
	})
	BrandModel update(
			@ApiParam(value = "ID of a brand", example = "1", required = true) 
			Long brandId,
			
			@ApiParam(name = "body", value = "Representation of a new brand with new data", required = true)
			BrandInput brandInput);
	
	@ApiOperation("Remove the brand by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Brand removed"),
		@ApiResponse(code = 404, message = "Brand not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of a Brand", example = "1", required = true)
			Long brandId);
	
}
